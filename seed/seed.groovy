/**
 * The Seed plug-in will give the following parameters to this scripts, available directly as variables:
 *
 * - raw parameters (seed generator input + scm branch)
 *   - PROJECT - raw project name, like nemerosa/seed in GitHub
 *   - PROJECT_CLASS
 *   - PROJECT_SCM_TYPE
 *   - PROJECT_SCM_URL
 *   - BRANCH - basic branch name in the SCM, like branches/xxx in SVN
 *
 * - computed parameters:
 *   - SEED_PROJECT: project normalised name
 *   - SEED_BRANCH: branch normalised name
 *
 * The jobs are generated directly at the level of the branch seed job, so no folder needs to be created for the
 * branch itself.
 */

boolean release = BRANCH.startsWith('release/')

// Build job
freeStyleJob("${SEED_PROJECT}-${SEED_BRANCH}-build") {
    logRotator(-1, 40)
    deliveryPipelineConfiguration('Commit', 'Build')
    scm {
        git {
            remote {
                url PROJECT_SCM_URL
                branch "origin/${BRANCH}"
            }
            wipeOutWorkspace()
            localBranch "${BRANCH}"
        }
    }
    jdk 'JDK8u25'
    steps {
        gradle '''\
clean
versionDisplay
versionFile
build
--info
--stacktrace
--profile
--parallel
--console=plain
'''
        environmentVariables {
            propertiesFile 'build/version.properties'
        }
    }
    publishers {
        archiveJunit("**/build/test-results/*.xml")
        tasks(
                '**/*.java,**/*.groovy,**/*.xml,**/*.html,**/*.js',
                '**/build/**,seed/**',
                'FIXME', 'TODO', '@Deprecated', true
        )
        if (release) {
            buildPipelineTrigger("${SEED_PROJECT}/${SEED_PROJECT}-${SEED_BRANCH}/${SEED_PROJECT}-${SEED_BRANCH}-release") {
                parameters {
                    gitRevision(true)
                }
            }
        }
    }
}

// Publication job
if (release) {
    freeStyleJob("${SEED_PROJECT}-${SEED_BRANCH}-release") {
        logRotator(-1, 40)
        deliveryPipelineConfiguration('Commit', 'Release')
        scm {
            git {
                remote {
                    url PROJECT_SCM_URL
                    branch "origin/${BRANCH}"
                }
                wipeOutWorkspace()
                localBranch "${BRANCH}"
            }
        }
        jdk 'JDK8u25'
        steps {
            gradle '''\
clean
versionDisplay
versionFile
build
publish
-x test
--info
--stacktrace
--profile
--parallel
--console=plain
'''
            environmentVariables {
                propertiesFile 'build/version.properties'
            }
        }
        publishers {
            archiveJunit("**/build/test-results/*.xml")
            buildDescription('', '${VERSION_DISPLAY} (${VERSION_FULL})', '', 'n/a')
            tasks(
                    '**/*.java,**/*.groovy,**/*.xml,**/*.html,**/*.js',
                    '**/build/**,seed/**',
                    'FIXME', 'TODO', '@Deprecated', true
            )
        }
    }
}