package net.nemerosa.resources.json;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonBuilder<J extends JsonNode> {

    J end();

}
