/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapConverter implements Converter<String, Map<Object, Object>> {
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> convert(String jsonAsString) {
		if (jsonAsString != null) {
			jsonAsString = jsonAsString.trim();
			if (!jsonAsString.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					Map<Object, Object> result = mapper.readValue(jsonAsString, Map.class);
					return result;
				} catch (IOException e) {
					log.error("Error mapping JSON as String parameter to a Map<Object, Object>.");
				}
			}
		}
		
		return Collections.emptyMap();
	}

}

