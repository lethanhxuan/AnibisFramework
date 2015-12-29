package com.nvg.anibis.swaggerapi;

import io.swagger.client.ApiException;
import io.swagger.client.api.SearchControllerApi;
import io.swagger.client.model.SearchParameterDto;
import io.swagger.client.model.SearchResultDto;

//This class is to define methods which help to interact to SearchController api (https://xbapi-stage.anibis.ch/swagger/ui/index#/)
public class SearchController {
	
	/**
	 *  *
	 */
	public static SearchResultDto apiSearch(String textkey) throws InterruptedException, ApiException {
		SearchParameterDto dto = new SearchParameterDto();
		SearchControllerApi sca = new SearchControllerApi();
		dto.setResultRows(50);
		dto.setSearchText(textkey);
		return sca.searchControllerPost(dto);
	}
}
