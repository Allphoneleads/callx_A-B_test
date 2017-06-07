package com.allphoneleads.api.util;

import java.io.IOException;

import com.allphoneleads.api.domain.IvrKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class IvrKeyDeserializer extends JsonDeserializer<IvrKey>{

	
	public IvrKey convertToEntityAttribute(String dbData) {
		if(dbData == null || dbData.equals("-1"))
			return IvrKey.NUll;
		
		switch (dbData) {
        case "1":
            return IvrKey.ONE;
        case "2":
            return IvrKey.TWO;
        case "3":
            return IvrKey.THREE;
        case "4":
            return IvrKey.FOUR;
        case "5":
            return IvrKey.FIVE;
        case "6":
            return IvrKey.SIX;
        case "7":
            return IvrKey.SEVEN;
        case "8":
            return IvrKey.EIGHT;
        case "9":
            return IvrKey.NINE;
        case "0":
            return IvrKey.ZERO;
        case "#":
            return IvrKey.HASH;
        case "*":
            return IvrKey.STAR;
        default:
        	 return IvrKey.NUll;
		}
	}
	
	@Override
	public IvrKey deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

			String value = jp.getText();
	        return convertToEntityAttribute(value);
	}

}
