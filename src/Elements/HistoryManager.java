package Elements;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private static final String FILE_NAME = "src/Elements/gameHistory.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static boolean saveToHistory = true;

    public static List<GameHistory> loadHistory() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, new TypeToken<List<GameHistory>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    public static void saveHistory(List<GameHistory> history) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(history, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addHistory(GameHistory newRecord) {
        List<GameHistory> history ;
        if(loadHistory()!=null){
             history = loadHistory();
        } else history = new ArrayList<>();
        if(saveToHistory){
            history.add(newRecord);
        }
        saveHistory(history);
    }
    public static void setSaveToHistory(boolean save){
        saveToHistory = save ;
    }
    public static String[] getHistoryString(){
        List<GameHistory> history = loadHistory();
        String[] array = new String[history.size()];
        int i = 0 ;
        for(GameHistory g : history){
            array[i] = g.toString();
            i++;
        }
        return array;
    }
}