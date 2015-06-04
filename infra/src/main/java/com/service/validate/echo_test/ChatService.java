package com.service.validate.echo_test;

import com.agent.AgentService;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.pages.echo_test.Chat;
import com.pages.echo_test.Info;
import com.pages.echo_test.InfraConstants;
import com.pages.echo_test.Menu;
import com.service.ReflectivePageFlow;
import com.service.activate.demo_app.DemoActivator;
import com.service.activate.echo_test.ChatActivator;
import com.ui.service.AppiumService;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * Created by asih on 09/03/2015.
 */


public class ChatService {

    private static final ChatService INSTANCE = new ChatService();
    private ChatActivator chatActivator = ChatActivator.getInstance();
    private Info info = new Info(true, true);
    private Menu menu = new Menu(true, true);
    private Chat chat = new Chat(true, true);
    private DemoActivator demoActivator = DemoActivator.getInstance();
    private static Rep mobileAgent;

    private ChatService(){
    }

    /**
     *
     * @return ChatValidator
     */

    public static ChatService getInstance(){
        return INSTANCE;
    }

    public void verifySkillRemainLegual(String skill) throws Exception {
        ReflectivePageFlow.invoke(Menu.class, menu, Arrays.asList(InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME));
        menu.getActivate().enterMenuInfo();

        ReflectivePageFlow.invoke(Info.class, info, Arrays.asList(InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME));
        info.getValidate().verifySkillRemainLegual(skill);
    }

    public boolean isMsgAppearInChat(String msg){
        if(mobileAgent == null) {
            ReflectivePageFlow.invoke(Chat.class, chat, Arrays.asList(InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME));
        }
        return chat.getValidate().isMsgAppearInChat(msg);
    }

    public boolean isSystemMsgAppearInTop(String msg, long timeOutInMilisec){
        if(mobileAgent == null) {
            ReflectivePageFlow.invoke(Chat.class, chat, Arrays.asList(InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME));
        }
        return chat.getValidate().isSystemMsgAppearInTop(msg, timeOutInMilisec);
    }

    public void verifyChatMsg(String msg){
        AppiumService.getInstance().implicitWait(1500);
        Assert.assertTrue("Message " + msg + " + do not appear in chat", isMsgAppearInChat(msg));
    }

    public void verifyChatSystemMsg(String msg){
        Assert.assertTrue("Message Agents are standing by... do not appear in chat", isSystemMsgAppearInTop("Agents are standing by...", 5000));
    }

    public void startAndValidateChat(AgentService service, List<Rep> repsState, List<AgentState> agentStates) throws Exception {
        service.logInAndSetState(repsState, agentStates);
        demoActivator.enterChat();
        verifyChatSystemMsg("Agents are standing by...");
    }

    public void activateAndValidateTwoWayMsg(AgentService service, String visitorMsg, String agentMsg) throws Exception {
        sendMsgByChatStatus(visitorMsg);
        Assert.assertTrue("Message " + visitorMsg + " + do not appear in chat", isMsgAppearInChat(visitorMsg));
        if(mobileAgent == null) {
            mobileAgent = service.prepareAgentForChat();
        }
        verifyChatMsg(visitorMsg);
        service.addChatLines(mobileAgent, agentMsg);
        verifyChatMsg(agentMsg);
        Assert.assertTrue("Chat last line " + agentMsg + "is not as expected ", service.verifyLatestChatLines(mobileAgent, agentMsg));
    }

    private void sendMsgByChatStatus(String visitorMsg) throws Exception {
        if(mobileAgent == null) {
            chatActivator.sendChatMsg(visitorMsg, true);
        }else{
            chatActivator.sendChatMsg(visitorMsg, false);
        }
    }

    private void ensSession() throws Exception {
        chatActivator.ensSession();
    }

    public void closeChat(AgentService service, Rep rep) throws Exception {
        ensSession();
        service.endChat(rep);
    }
}