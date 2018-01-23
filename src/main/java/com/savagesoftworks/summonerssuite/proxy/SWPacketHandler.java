package com.savagesoftworks.summonerssuite.proxy;

import java.util.Arrays;
import java.util.List;

import com.savagesoftworks.summonerssuite.parser.Decryptor;
import com.savagesoftworks.summonerssuite.parser.Parser;
import com.savagesoftworks.summonerssuite.parser.datalisteners.CommandListener;
import com.savagesoftworks.summonerssuite.parser.datalisteners.LoginListener;
import com.savagesoftworks.summonerssuite.proxy.library.HttpMessageHandler;
import com.savagesoftworks.summonerssuite.proxy.library.HttpMessageRequest;
import com.savagesoftworks.summonerssuite.proxy.library.HttpMessageResponse;

public class SWPacketHandler implements HttpMessageHandler {
	
	//Status codes that will not be decrypted
	private static final List<Integer> uninterestingCodes = Arrays.asList(
		204, 405, 408, 500
	);
	
	public SWPacketHandler() {
		
		Parser.addListener(new CommandListener());
		Parser.addListener(new LoginListener());
		
	}
	
	@Override
	public void failed(Exception exception) {}

	@Override
	public void failedRequest(HttpMessageRequest request, Exception exception) {}

	@Override
	public void failedResponse(HttpMessageResponse response, HttpMessageRequest request, Exception exception) {}

	@Override
	public void receivedRequest(HttpMessageRequest request) {}

	@Override
	public void receivedResponse(HttpMessageResponse response, HttpMessageRequest request) {
		
		try {
			
			String requestPath = request.getUri().toURL().getPath();
			String hostName = request.getToHost();
			int statusCode = response.getStatusCode();
			
			if(hostName.contains("summonerswar") && requestPath.contains("/api") && !uninterestingCodes.contains(statusCode)) {

				byte[] data = response.getBodyContent();
				Parser.parseData(Decryptor.decryptResponse(data));
				
			}
			
		} catch (Exception exception) {

			
		}
		
	}


}