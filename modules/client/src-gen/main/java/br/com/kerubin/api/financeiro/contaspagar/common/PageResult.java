/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.common;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PageResult<T> extends PageImpl<T>  {

	private static final long serialVersionUID = 7761447395276278048L;

	//Based on: https://stackoverflow.com/questions/34099559/how-to-consume-pageentity-response-using-spring-resttemplate
	@JsonCreator
    // Note: I don't need a sort, so I'm not including one here.
    // It shouldn't be too hard to add it in tho.
    public PageResult(@JsonProperty("content") List<T> content,
                      @JsonProperty("number") int number,
                      @JsonProperty("size") int size,
                      @JsonProperty("totalElements") Long totalElements) {
		
        super(content, PageRequest.of(number, size), totalElements);
        
    }

}
