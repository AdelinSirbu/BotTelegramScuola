import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

class EchoBot extends TelegramLongPollingBot {
    public String getBotUsername() {
        return "Echo Bot";
    }
    @Override
    public String getBotToken() {
        // inserire qui il proprio token
        return "6869733081:AAFSpnySHmxroJEBHcfopW7WJ5lnp4fJzfo";
    }
    public void onUpdateReceived(Update update) {
        String msg = update.getMessage().getText();
        String chatId=update.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            // gestione errore in invio
        }
    }
}
//
//
//public class Main {
//    public static void main(String[] args) {
//
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new EchoBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//}
