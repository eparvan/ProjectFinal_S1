package org.example.presentation;

import org.example.data.NewsDataSource;
import org.example.domain.NewsParser;
import org.example.presentation.menu.Menu;

import java.util.Scanner;

public class Main {
    private static final Scanner console = new Scanner(System.in);
    private static final Menu menu = new Menu();
    private static Boolean isRunning = true;
    private static NewsDataSource dataSource = new NewsDataSource("src/main/java/org/example/news_data.json");
    private static NewsParser parser = new NewsParser(dataSource);
    public static void main(String[] args) {
        setUpMenu(parser);
        menu.printMenu();
        while (isRunning){
            System.out.println("\nAlege optiunea:");
            menu.onMenuItemSelected(console.nextInt());
        }
    }

    private static void setUpMenu(NewsParser parser) {
        menu.addMenuItem("Afisarea numarului total de articole", ()->{
            System.out.println("Numarul de articole: " + parser.getNumberOfArticles());
        });
        menu.addMenuItem("Afisarea antetelor articolelor",()->{

        });
        menu.addMenuItem("Afisarea antetelor articolelor dupa ID",()->{

        });
        menu.addMenuItem("Afisarea descrierii articolelor dupa ID",()->{

        });
        menu.addMenuItem("Afisarea descrierii depline a articolelor dupa ID",()->{

        });
        menu.addMenuItem("Cautare si afisare a articolelor dupa cuvintele cheie",()->{

        });
        menu.addMenuItem("Cautare si afisare a articolelor dupa antet",()->{

        });
        menu.addMenuItem("Cautare si afisare a articolelor dupa descriere",()->{

        });
        menu.addMenuItem("Cautare si afisare a articolelor dupa autor",()->{

        });
        menu.addMenuItem("Afisarea numarului de cuvinte din articol",()->{
            System.out.print("Introdu identificatorul articolului sau apasa enter:");
            String articleId = new Scanner(System.in).nextLine();
            if(articleId.isBlank()){
                Integer totalWordsCount = parser.getTotalWordsCount();
                System.out.print("Numarul total de cuvinte: " + totalWordsCount);
            }else{
                Integer wordsCount = parser.getWordsCountForArticle(Integer.parseInt(articleId));
                System.out.print("In articolul : " +articleId+ " numarul de cuvinte este: " + wordsCount);
            }

        });
        menu.addMenuItem("Afișează numărul de apariții ale unui anumit cuvânt în articol",()->{

        });
        menu.addMenuItem("Meniul aplicatiei ", menu::printMenu);
        menu.addMenuItem("Iesire", ()->isRunning=false);
    }
}