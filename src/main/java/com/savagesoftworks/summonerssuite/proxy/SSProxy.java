package com.savagesoftworks.summonerssuite.proxy;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.savagesoftworks.summonerssuite.components.Component;
import com.savagesoftworks.summonerssuite.proxy.library.Proxy;
import com.savagesoftworks.summonerssuite.proxy.library.ProxyRegistry;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SSProxy extends Component {

	private static final int DEFAULT_PORT = 8080;
	
	private final StringProperty ip;
	private final IntegerProperty port;
	
	private final StringProperty proxyInformation;
	
	private final Proxy proxy;
	
	public SSProxy() {
		
		Properties props = new Properties();
        props.setProperty("log4j.rootLogger","OFF, stdout");
        props.setProperty("log4j.appender.stdout","org.apache.log4j.ConsoleAppender");
        props.setProperty("log4j.appender.stdout.layout","org.apache.log4j.PatternLayout");
        props.setProperty("log4j.appender.stdout.layout.ConversionPattern","%5p [%t] (%F:%L) - %m%n");
        PropertyConfigurator.configure(props);
        
		proxy = new Proxy(getNetworkIP(), DEFAULT_PORT, 0);
		ProxyRegistry.addHandler(new SWPacketHandler());
        
		proxyInformation = new SimpleStringProperty("No proxy running...");

		ip = new SimpleStringProperty();
		port = new SimpleIntegerProperty();
        
        ChangeListener<Object> listener = new ChangeListener<Object>(){

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				
				updateProxyInformation();
				
			}

        };
        
        ip.addListener(listener);
        ip.set(getIP());
        port.addListener(listener);
        port.set(getPort());
        
		proxy.start();

	}
	
	public void updateProxyInformation() {
		
		proxyInformation.set("Proxy running on " + ip.get() + " (" + port.get() + ")");
		
	}
	
	public void setPort(Integer port) {
		
		this.port.set(port);
		proxy.setPort(port);
		
	}
	
	public StringProperty getProxyInformation() {
		
		return proxyInformation;
		
	}

	public String getIP() {
		
		return proxy.getInetAddress().getHostAddress();
		
	}
	
	public Integer getPort() {
		
		return proxy.getPort();
		
	}
	
	private static InetAddress getNetworkIP() {
		
		try {
			
			for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
					
				Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
				
				while(addresses.hasMoreElements()) {
					
					InetAddress address = addresses.nextElement();
					
					if(address.isSiteLocalAddress()) { 
						
						return address; 
						
					}
					
				}
				
			}
			
		} catch (SocketException exception) {
			exception.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public void shutdown() {
		
		proxy.shutdown();
		
	}
	
}