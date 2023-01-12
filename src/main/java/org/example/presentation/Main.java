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
            System.out.println("Lista antetelor articolelor: ");
            for (String item: parser.printAllArticles()) {
                System.out.println(item);
            }
        });
        menu.addMenuItem("Afisarea antetelor articolelor dupa ID",()->{
            System.out.print("Introdu identificatorul articolului sau apasa enter:");
            String articleId = new Scanner(System.in).nextLine();
            if(Integer.parseInt(articleId) >= 0 && Integer.parseInt(articleId) < parser.printAllArticles().size()){
                System.out.println("Antetul cu numarul " + articleId + " este:\n" + parser.printAllArticles().get(Integer.parseInt(articleId)));
            }else{
                System.out.println("Nu exista astfel de antet!");
            }
        });
        menu.addMenuItem("Afisarea descrierii articolelor dupa ID",()->{
            System.out.print("Introdu identificatorul articolului sau apasa enter:");
            String articleId = new Scanner(System.in).nextLine();
            Integer id = Integer.parseInt(articleId);
            if(id >= 0 && id < parser.printArticleDescriptionById(id).size()){
                System.out.println("Descrierea articolului cu numarul " + articleId + " este:\n" + parser.printArticleDescriptionById(id).get(id));
            }else{
                System.out.println("Nu exista astfel de articol!");
            }
        });
        menu.addMenuItem("Afisarea descrierii depline a articolelor dupa ID",()->{
            System.out.print("Introdu identificatorul articolului sau apasa enter:");
            String articleId = new Scanner(System.in).nextLine();
            Integer id = Integer.parseInt(articleId);
            if(id >= 0 && id < parser.printArticleFullDescriptionById(id).size()){
                System.out.println("Descrierea deplina a articolului cu numarul " + articleId + " este:\n" + parser.printArticleFullDescriptionById(id).get(id));
            }else{
                System.out.println("Nu exista astfel de articol!");
            }
        });
        menu.addMenuItem("Cautare si afisare a articolelor dupa cuvintele cheie",()->{
            System.out.print("Introdu cuvintul cheie pentru cautare:");
            String key = new Scanner(System.in).nextLine();
            if(key.isBlank() || parser.searchByKeywords(key).size()==0){
                System.out.println("Nu ati introdus nimic.");
            }
            else{
                System.out.println("Lista articolelor ce contin cuvintul: " +key+ " este:\n");
               for (String item: parser.searchByKeywords(key)) {
                    System.out.println(item);
                }
            }

        });
        menu.addMenuItem("Cautare si afisarea articolelor dupa antet",()->{

        });
        menu.addMenuItem("Cautare si afisarea articolelor dupa descriere",()->{

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