package com.fpt.fitme.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.fitme.entity.appuser.AppUser;
import com.github.fge.jsonpatch.JsonPatch;

public class JsonPatcherUtil {

    public static Object applyPatch(JsonPatch patch, Object target) {
        ObjectMapper objectMapper;
        Object result = null;
        try {
            objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(target, JsonNode.class));
            result = objectMapper.treeToValue(patched, AppUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
