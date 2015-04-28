package com.service.activate.echo_test;

import com.pages.echo_test.Chat;
import com.pages.echo_test.InfraConstants;
import com.pages.echo_test.Menu;
import com.service.ReflectivePageFlow;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by asih on 09/03/2015.
 */
public class ChatActivator {

    private static final ChatActivator INSTANCE = new ChatActivator();
    private static final Logger logger = Logger.getLogger(ChatActivator.class);
    private long timeOutInMilisec = 15000;
    private final long waitForPageSourceInterval = 200;

    private Chat chatPage = new Chat(true, true);

    private ChatActivator(){

    }

    public static ChatActivator getInstance(){
        return INSTANCE;
    }

    public void sendChatMsg(String msg) throws Exception {

        while (!chatPage.validateInPage()){
            try {
                Thread.sleep(waitForPageSourceInterval);
                timeOutInMilisec -= waitForPageSourceInterval;
                if(timeOutInMilisec <= 0){
                    GeneralUtils.handleError("Time out finished without getting chat",
                            new Exception("Time out finished without finding results"));
                    return;
                }
            }catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);
                return;
            }
        }
        ReflectivePageFlow.invoke(Chat.class, chatPage, Arrays.asList(InfraConstants.PREPARE_ELEMENTS_M_NAME));
        chatPage.getActivate().sendMsg(msg);
    }

    public void ensSession(){
        chatPage.getActivate().ensSession();
    }

}
