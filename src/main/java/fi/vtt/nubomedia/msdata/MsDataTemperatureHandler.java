/*
 * (C) Copyright 2015 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */

package fi.vtt.nubomedia.msdata;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.kurento.client.EventListener;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;

import org.kurento.jsonrpc.JsonUtils;
import org.kurento.module.msdatamodule.*;
import org.kurento.module.msdatamodule.KmsDetectFaces;
import org.kurento.module.msdatamodule.KmsShowFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author Markus Ylikerala 
 * @since 6.0.0
 */
public class MsDataTemperatureHandler extends BaseHandler {
    
    private final Logger log = LoggerFactory.getLogger(MsDataTemperatureHandler.class);
    private static final Gson gson = new GsonBuilder().create();
    
    public void createPipeline(UserSession userSession, JsonObject jsonMessage){
	try{
	    WebRtcEndpoint webRtcEndpoint = userSession.getWebRtcEndpoint();
	    MediaPipeline mediaPipeline = userSession.getMediaPipeline();
	    
	    KmsShowFaces kmsShowData = new KmsShowFaces.Builder(mediaPipeline).build();	    
	    webRtcEndpoint.connect(kmsShowData);
	    kmsShowData.connect(webRtcEndpoint);
	}
	catch(Exception e){
	    throw new RuntimeException(e);
	}
    }
}
