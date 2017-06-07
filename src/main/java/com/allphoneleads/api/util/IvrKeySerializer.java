package com.allphoneleads.api.util;

import java.io.IOException;

import com.allphoneleads.api.domain.IvrKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IvrKeySerializer extends JsonSerializer<IvrKey> {
	
	//private static DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
	
//	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	public String convertToDatabaseColumn(IvrKey attribute) {
		if(attribute == null)
			return null;
		
		switch (attribute) {
        case ONE:
            return "1";
        case TWO:
            return "2";
        case THREE:
            return "3";
        case FOUR:
            return "4";
        case FIVE:
            return "5";
        case SIX:
            return "6";
        case SEVEN:
            return "7";
        case EIGHT:
            return "8";
        case NINE:
            return "9";
        case ZERO:
            return "0";
        case HASH:
            return "#";
        case STAR:
            return "*";
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
		}
	}
	
	@Override
    public void serialize(IvrKey value, JsonGenerator gen,
            SerializerProvider arg2) throws IOException,
            JsonProcessingException {

        gen.writeString(convertToDatabaseColumn(value));
    }

}
