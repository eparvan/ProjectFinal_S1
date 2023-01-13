package org.example.presentation;

import org.example.data.NewsDataSource;
import org.example.data.models.Article;
import org.example.domain.NewsParser;
import org.example.presentation.menu.Menu;

import java.util.List;
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
        menu.addMenuItem("Meniul aplicatiei ", menu::printMenu);
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
            try{
                Integer id = Integer.parseInt(articleId);
                List<String> listArticle =parser.printAllArticles();
                if(id >= 0 && id < listArticle.size()){
                    System.out.println("Antetul cu numarul " + id + " este:\n" + listArticle.get(id));
                }else{
                    System.out.println("Nu exista astfel de antet!");
                }

            }catch(NumberFormatException e){
                System.out.println("Nu ati introdus un numar corect!");
            }

        });
        menu.addMenuItem("Afisarea descrierii articolelor dupa ID",()->{
            System.out.print("Introdu identificatorul articolului sau apasa enter:");
            String articleId = new Scanner(System.in).nextLine();
            try{
                Integer id = Integer.parseInt(articleId);
                List<String> listArticle = parser.printArticleDescriptionById(id);
                if(id >= 0 && id < listArticle.size()){
                    System.out.println("Descrierea articolului cu numarul " + id + " este:\n" + listArticle.get(id));
                }else{
                    System.out.println("Nu exista astfel de articol!");
                }
            }catch(NumberFormatException e){
                System.out.println("Nu ati introdus un numar corect!");
            }
        });
        menu.addMenuItem("Afisarea descrierii depline a articolelor dupa ID",()->{
            System.out.print("Introdu identificatorul articolului sau apasa enter:");
            String articleId = new Scanner(System.in).nextLine();
            try{
                Integer id = Integer.parseInt(articleId);
                List<String> listArticle = parser.printArticleFullDescriptionById(id);
                if(id >= 0 && id < listArticle.size()){
                    System.out.println("Descrierea deplina a articolului cu numarul " + id + " este:\n" + listArticle.get(id));
                }else{
                    System.out.println("Nu exista astfel de articol!");
                }
            }catch(NumberFormatException e){
                System.out.println("Nu ati introdus un numar corect!");
            }
        });
        menu.addMenuItem("Cautare si afisare a articolelor dupa cuvintele cheie",()->{
            System.out.print("Introdu cuvintul cheie pentru cautare:");
            String key = new Scanner(System.in).nextLine();
            List<String> listArticle = parser.searchByKeywords(key);
            if(key.isBlank() || listArticle.size()==0){
                System.out.println("Nu ati introdus nimic.");
            }
            else{
                System.out.println("Lista articolelor ce contin cuvintul: " +key+ " este:\n");
               for (String item: listArticle) {
                    System.out.println(item);
                }
            }
        });
        menu.addMenuItem("Cautare si afisarea articolelor dupa antet",()->{
            System.out.print("Introdu titlul pentru cautare:");
            String titleSearch = new Scanner(System.in).nextLine();
            List<Article> searchResult = parser.searchByTitle(titleSearch);
            if(titleSearch.isBlank() || searchResult.size()==0){
                System.out.println("Nu ati introdus nimic/sau nu s-a gasit nimic.");
            }
            else{
                System.out.println("Lista articolelor ce contin titlul: " +titleSearch+ " este:\n");
                for (Article item: searchResult) {
                    System.out.println(item.title);
                }
            }
        });
        menu.addMenuItem("Cautare si afisarea articolelor dupa descriere",()->{
            System.out.print("Introdu descrierea pentru cautare:");
            String descriptionSearch = new Scanner(System.in).nextLine();
            List<Article> searchResult = parser.searchByDescription(descriptionSearch);
            if(descriptionSearch.isBlank() || searchResult.size()==0){
                System.out.println("Nu ati introdus nimic.");
            }
            else{
                System.out.println("Lista articolelor ce contin descrierea: " +descriptionSearch+ " este:\n");
                for (Article item: searchResult) {
                    System.out.println(item.title);
                }
            }
        });
        menu.addMenuItem("Cautare si afisare a articolelor dupa autor",()->{
            System.out.print("Introdu autorul pentru cautare:");
            String creatorSearch = new Scanner(System.in).nextLine();
            List<Article> searchResult = parser.searchByCreator(creatorSearch);
            if(creatorSearch.isBlank() || searchResult.size()==0){
                System.out.println("Nu ati introdus nimic.");
            }
            else{
                System.out.println("Lista articolelor ce sunt create de : " +creatorSearch+ " este:\n");
                for (Article item: searchResult) {
                    System.out.println(item.title);
                }
            }
        });
        menu.addMenuItem("Afisarea numarului de cuvinte din articol",()->{
            System.out.print("Introdu identificatorul articolului sau apasa enter:");
            String articleId = new Scanner(System.in).nextLine();

            if(articleId.isBlank()){
                Integer totalWordsCount = parser.getTotalWordsCount();
                System.out.print("Numarul total de cuvinte: " + totalWordsCount);
            }else{
                try{
                    Integer id = Integer.parseInt(articleId);
                    Integer wordsCount = parser.getWordsCountForArticle(id);
                    System.out.print("In articolul : " +id+ " numarul de cuvinte este: " + wordsCount);

                }catch(NumberFormatException e){
                    System.out.println("Nu ati introdus numar corect!");
                }

            }
        });
        menu.addMenuItem("Afișează numărul de apariții ale unui anumit cuvânt în articol",()->{
            System.out.print("Introdu cuvintul cheie pentru cautare:");
            String word = new Scanner(System.in).nextLine();
            System.out.print("Introdu identificatorul articolului in care se va cauta sau apasa enter pentru a cauta in toate:");
            String articleId = new Scanner(System.in).nextLine();
            Integer id=0;

            if(word.isBlank()){
                System.out.println("Nu ati introdus nimic.");
            }
            else{
                if(articleId.isBlank()){
                    id = -1;
                }else{
                    try{
                        id = Integer.parseInt(articleId);
                    }catch(NumberFormatException e){
                        System.out.println("Nu ati introdus un numar corect! se va cauta in toate articolele");
                        id=0;
                    }
                }
            }
            Integer searchResult = parser.searchWordOccurence(word, id);
            System.out.println("Cuvintul: " +word+ " este intilnit de:" + searchResult);
        });
        menu.addMenuItem("Iesire", ()->isRunning=false);
    }
}