package com.allphoneleads.api.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.allphoneleads.api.domain.IvrKey;

@Converter(autoApply = true)
@Configuration
@EnableAutoConfiguration
public class IvrKeyConverter implements AttributeConverter<IvrKey, String>{

	@Override
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
	public IvrKey convertToEntityAttribute(String dbData) {
		if(dbData == null)
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

	
	
}
