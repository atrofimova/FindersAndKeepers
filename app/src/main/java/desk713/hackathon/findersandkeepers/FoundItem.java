package desk713.hackathon.findersandkeepers;

import android.graphics.Bitmap;

import java.sql.Timestamp;

public class FoundItem {
    public static String id;
    public static Bitmap picture;
    public static String type;
    public static String[] questions = new String[3];
    public static String[] answers = new String[3];
    public static String description;
    public static String phone;
    public static String name;

    public static void setType (String type){
        FoundItem.type = type;
    }
    public static void setPhone (String phone){
        FoundItem.phone = phone;
    }
    public static void setName (String name){
        FoundItem.name = name;
    }
    public static void setQuestion(int id, String question){
     FoundItem.questions[id] = question;
    }

     public static void setAnswer(int id, String answer){
     FoundItem.answers[id] = answer;
    }

    public static void setDescription(String description){
     FoundItem.description = description;
    }

    public static void setId(){
        FoundItem.id = (new Timestamp(System.currentTimeMillis())).toString();
    }


    public static void setPicture(Bitmap bitmap){
        FoundItem.picture = bitmap;
    }

    //TODO
    public Boolean isReadyForSubmission(){
        return true;
    }



}
