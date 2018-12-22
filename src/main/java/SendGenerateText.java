import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class SendGenerateText {

    private static final ArrayList<String[]> STACK = new ArrayList<>();
    static {
        String[] lastNames = new String[]
                        {"Иван", "Борис", "Виктор", "Николай", "Владимир", "Владислав", "Петр", "Константин", "Никита", "Александр"},
                firstName = new String[]
                        {"Зайцев", "Лисицын", "Медведев", "Чайка", "Соколов", "Скворцов", "Петросяношвилли", "Хайзенберг", "Тантумвердефорте", "Бженчишчикевич"},
                group = new String[]
                        {"ИСТбд-11", "ИСТбд-21", "ИСТбд-22", "ИСТбд-31", "ИСТбд-41"},
                progLang = new String[]
                        {"Java", "C#", "Python", "Erlang", "Scala", "Kotlin", "C++", "C", "Ada", "Ruby", "Forth"};
        STACK.add(lastNames);
        STACK.add(firstName);
        STACK.add(group);
        STACK.add(progLang);
    }

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            Scanner scanner = new Scanner(System.in);
            String message;
            int num;
            while (true) {
                System.out.println("x. Отправка сериализованного в XML класса");
                System.out.println("j. Отправка сериализованного в JSON класса");
                System.out.println("\"Любое число\". Количество генирируемых строк для отпраки");
                System.out.println("0. Выход из программы");
                message = scanner.nextLine();
                switch (message.toLowerCase()) {
                    case "0":
                        System.exit(0);
                        break;
                    case "j":
                        message = generateStroke();
                        String[] massJ = message.split(" ");
                        message = new SerialJson().serial(massJ[0], massJ[1], massJ[2], massJ[3]);
                        push(0, message, channel, "queue_json");
                        break;
                    case "x":
                        message = generateStroke();
                        String[] massX = message.split(" ");
                        message = new SerialXML().serial(massX[0], massX[1], massX[2], massX[3]);
                        push(0, message, channel, "queue_xml");
                        break;
                        default:
                            try {
                                num = Integer.parseInt(message);
                                push(num, null, channel, "lab");
                            } catch (NumberFormatException e) {
                                System.err.println("Нет совпадений, число введено не верно");
                            }
                }
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void push(int num, String message, Channel channel, String queueName) throws IOException {
        channel.queueDeclare(queueName, true, false, false, null);
        if (num == 0 && message.length() > 0)
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
        else
            for (int i = 0; i < num; i++) {
                message = generateStroke();
                channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            }
    }

    private static String generateStroke() {
        StringBuilder str = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                int item = new Random().nextInt(STACK.get(i).length - 1);
                if (str.length() == 0) str.append(STACK.get(i)[item]);
                else str.append(" ").append(STACK.get(i)[item]);
            }
        return str.toString();
    }
}