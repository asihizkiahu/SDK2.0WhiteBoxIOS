package com.service.validate.echo_test;

import com.agent.AgentService;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.liveperson.api.ChatApiException;
import com.pages.Engagement;
import com.pages.echo_test.Chat;
import com.pages.echo_test.Info;
import com.pages.echo_test.InfraConstants;
import com.pages.echo_test.Menu;
import com.service.ReflectivePageFlow;
import com.service.activate.demo_app.DemoActivator;
import com.service.activate.echo_test.ChatActivator;
import com.ui.service.AppiumService;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
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
    private Engagement engagement = new Engagement(true, true);
    private DemoActivator demoActivator = DemoActivator.getInstance();
    private static final Logger logger = Logger.getLogger(ChatService.class);
    private SysMsgConstants constants = new SysMsgConstants();

    private Rep currentAgent;
    private boolean isChatStarted = false;

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
        ReflectivePageFlow.invoke(
                Menu.class, menu, Arrays.asList(
                        InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME
                )
        );
        menu.getActivate().enterMenuInfo();

        ReflectivePageFlow.invoke(
                Info.class, info, Arrays.asList(
                        InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME
                )
        );
        info.getValidate().verifySkillRemainLegual(skill);
    }

    public void verifyIsInEngagementPage(){
        ReflectivePageFlow.invoke(
                Engagement.class, engagement, Arrays.asList(
                        InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME
                )
        );
    }

    public boolean isMsgAppears(String msg, long timeOutInMilisec, MessageType msgType){
        try {
            if (!isChatStarted) {
                ReflectivePageFlow.invoke(
                        Chat.class, chat, Arrays.asList(
                                InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME)
                );
            }
            if(msgType == MessageType.CHAT) {
                return chat.getValidate().isMsgAppearInChat(msg);
            }else if(msgType == MessageType.SYSTEM){
                return chat.getValidate().isSystemMsgAppearInTop(msg, timeOutInMilisec);
            }
        }catch (Exception e){
            chat.getActivate().ensSession();
            GeneralUtils.handleError("Can't find msg " + msg + " in chat", e);
        }
        return false;
    }

    public void verifyChatMsg(String msg, MessageType msgType){
        if(msgType == MessageType.CHAT) {
            AppiumService.getInstance().implicitWait(2500);
            Assert.assertTrue(
                    "Message " + msg + " + do not appear in chat",
                    isMsgAppears(msg, 5000, MessageType.CHAT)
            );
        }else if(msgType == MessageType.SYSTEM) {
            Assert.assertTrue(
                    msg + " do not appear on top of chat",
                    isMsgAppears(msg, 5000, MessageType.SYSTEM)
            );
        }
    }

    public void startAndValidateChat(AgentService service, List<Rep> repsState, List<AgentState> agentStates, Rep agent) {
        service.logInAndSetState(repsState, agentStates);
        try {
            demoActivator.enterChat();
        } catch (Exception e) {
            logger.warn("Failed to login as agent " + e.getMessage());
        } finally {
            verifyChatMsg(constants.AGENTS_STANDING_BY, MessageType.SYSTEM);
            verifyChatMsg(constants.SEND_MSG_TO_OUR_REPS, MessageType.SYSTEM);
            currentAgent = agent;
        }
    }

    public void handleMsgFlow(AgentService service, String visitorMsg, String agentMsg, boolean isCheckSpecificAgent, String repNickName, long timeOut, boolean isVerifySysMsg){
        try {
            sendMsgByChatStatus(visitorMsg);
            if(isVerifySysMsg) {
                verifyChatMsg(constants.AGENT_ARRIVE_SHORTLY, MessageType.SYSTEM);
            }
        } catch (Exception e) {
            logger.warn("Failed to send visitor msg " + e.getMessage());
            return;
        }
        verifyChatMsg(visitorMsg, MessageType.CHAT);
        if(!isChatStarted) {
            service.prepareAgentForChat(currentAgent);
            if(isVerifySysMsg) {
                verifyChatMsg(constants.CHAT_WITH_SYS_MSG + constants.AGENT_NICK_NAME, MessageType.SYSTEM);
            }
            isChatStarted = true;
        }
        handleAgentMsgFlow(service, agentMsg, isCheckSpecificAgent, repNickName, timeOut, isVerifySysMsg);
    }

    private void handleAgentMsgFlow(AgentService service, String agentMsg, boolean isCheckSpecificAgent, String repNickName, long timeOut, boolean isVerifySysMsg){
        try {
            currentAgent.setAgentTyping(true);
            if(isVerifySysMsg) {
                verifyChatMsg(constants.AGENT_IS_TYPING, MessageType.SYSTEM);
            }
        } catch (ChatApiException | IOException e) {
            logger.warn("Failed to set agent typing " + e.getMessage());
        }
        service.addChatLines(currentAgent, agentMsg);
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            logger.warn("Failed to wait " + e.getMessage());
        }
        if(isVerifySysMsg){
            try {
                logger.info("Waiting 3 minutes for agent delayed response system message");
                Thread.sleep(200000);
                verifyChatMsg(constants.DELAYED_RESP, MessageType.SYSTEM);
            } catch (InterruptedException e) {
                logger.warn("Failed to wait " + e.getMessage());
            }
        }
        assertAgent(service, agentMsg, isCheckSpecificAgent, repNickName);
    }

    private void assertAgent(AgentService service, String agentMsg, boolean isCheckSpecificAgent, String repNickName){
        verifyAgentMsg(agentMsg, isCheckSpecificAgent, repNickName);
        Assert.assertTrue("Chat last line " + agentMsg + "is not as expected ",
                service.verifyLatestChatLines(
                        currentAgent,
                        agentMsg
                )
        );
    }

    private void verifyAgentMsg(String agentMsg, boolean isCheckSpecificAgent, String repNickName){
        if(!isCheckSpecificAgent) {
            verifyChatMsg(agentMsg, MessageType.CHAT);
        }else{
            verifyChatMsg( repNickName + ": " + agentMsg, MessageType.CHAT);
        }
    }

    public void dismissSession() {
        ReflectivePageFlow.invoke(
                Chat.class, chat, Arrays.asList(
                        InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME
                )
        );
        chat.getActivate().dismissSession();
    }

    private void sendMsgByChatStatus(String visitorMsg) throws Exception {
        if(!isChatStarted) {
            chatActivator.sendChatMsg(visitorMsg, true);
        }else{
            chatActivator.sendChatMsg(visitorMsg, false);
        }
    }

    public void ensSession(AgentService service, Rep rep) throws Exception {
        chatActivator.ensSession();
        service.endChat(rep);
        isChatStarted = false;
    }

    public void closeChat(AgentService service, Rep rep) throws Exception {
        ensSession(service, rep);
        service.endChat(rep);
        isChatStarted = false;
    }

    public void setIsChatStarted(boolean isChatStarted){
        this.isChatStarted = isChatStarted;
    }

    public enum MessageType{

        CHAT, SYSTEM;
    }

    private class SysMsgConstants{

        private final String CHAT_WITH_SYS_MSG = "You are now chatting with ";
        private final String AGENT_ARRIVE_SHORTLY = "Thank you for choosing to chat with us.  An agent will be with you shortly.";
        private final String AGENT_NICK_NAME = "Asi the king of agents";
        private final String AGENT_IS_TYPING = "Agent is typing...";
        private final String AGENTS_STANDING_BY = "Agents are standing by...";
        private final String SEND_MSG_TO_OUR_REPS = "Send a message to our live service reps for immediate help";
        private final String DELAYED_RESP = "I'm sorry for the delay. I'll be right with you.";
    }

}
