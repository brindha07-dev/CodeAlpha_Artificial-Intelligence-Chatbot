public class ChatBot {


    public String getResponse(String input) {

        // Basic NLP Processing
        input = input.toLowerCase().trim();
        input = input.replaceAll("[^a-zA-Z ]", "");

        // Greetings
        if (input.contains("hello") || input.contains("hi")) {
            return "Hey there! 😊 How can I help you today?";
        }

        // Name
        else if (input.contains("name")) {
            return "I am CodeAlpha AI ChatBot 🤖 Nice to meet you!";
        }

        // Creator
        else if (input.contains("who created you")) {
            return "I was created by a smart Java developer during CodeAlpha internship 😎";
        }

        // How are you
        else if (input.contains("how are you")) {
            return "I'm always happy and ready to chat! 😄 What about you?";
        }

        // Internship
        else if (input.contains("internship")) {
            return "Internships help you gain real-world experience and skills 🚀";
        }

        // Java
        else if (input.contains("java")) {
            return "Java is a powerful Object-Oriented Programming language ☕";
        }

        // Joke
        else if (input.contains("joke")) {
            return "Why do programmers prefer dark mode? 😆 Because light attracts bugs!";
        }

        // Funny
        else if (input.contains("funny")) {
            return "I would tell you a UDP joke... but you might not get it 😂";
        }

        // Love
        else if (input.contains("love")) {
            return "I love coding and chatting with you 💙";
        }

        // Sad
        else if (input.contains("sad")) {
            return "Don't be sad! Even bugs get fixed 🐞✨";
        }

        // Food
        else if (input.contains("food")) {
            return "I don't eat food, but I consume data 😋";
        }

        // Sleep
        else if (input.contains("sleep")) {
            return "I never sleep... I'm 24/7 online 😎";
        }

        // Friend
        else if (input.contains("friend")) {
            return "Of course! I am your virtual friend 🤝";
        }

        // Bye
        else if (input.contains("bye")) {
            return "Bye bye! 👋 Come back soon!";
        }

        // Default
        else {
            return "Hmm 🤔 I am still learning. Can you ask something else?";
        }
    }
}



