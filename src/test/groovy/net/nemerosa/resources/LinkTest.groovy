package net.nemerosa.resources

import org.junit.Test

class LinkTest {

    @Test
    void 'Of'() {
        def link = Link.of('name', URI.create("urn:test"))
        assert link.name == 'name'
        assert link.href.toString() == 'urn:test'
    }

}
