import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite sua chave da API: ");
        String apiKey = sc.next();
        byte userAnswer = 0;

        while (userAnswer != 7) {
            System.out.println();
            menu();
            System.out.println();
            userAnswer = sc.nextByte();
            switch (userAnswer) {
                case 1:
                    converterMenu(apiKey, "USD", "ARS");
                    break;
                case 2:
                    converterMenu(apiKey, "ARS", "USD");
                    break;
                case 3:
                    converterMenu(apiKey, "USD", "BRL");
                    break;
                case 4:
                    converterMenu(apiKey, "BRL", "USD");
                    break;
                case 5:
                    converterMenu(apiKey, "USD", "COP");
                    break;
                case 6:
                    converterMenu(apiKey, "COP", "USD");
                    break;
                case 7:
                    System.out.println("SAINDO...");
                    return;
                default:
                    System.out.println("Erro. Digite um numero entre 1 e 7.");
            }
        }
    }

    public static void menu() {
        System.out.println("=============================");
        System.out.println("1 - Dólar =>> Peso Argentino");
        System.out.println("2 - Peso Argentino =>> Dólar");
        System.out.println("3 - Dólar =>> Real Brasileiro");
        System.out.println("4 - Real Brasileiro =>> Dólar");
        System.out.println("5 - Dólar =>> Peso Colombiano");
        System.out.println("6 - Peso Colombiano =>> Dólar");
        System.out.println("7 - Sair");
        System.out.println("=============================");
    }

    public static double convertedCurrency(CurrencyConverter currency, String apiKey) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CurrencyApiRequest currencyApiRequest = new CurrencyApiRequest(apiKey);
        String json = currencyApiRequest.apiRequest(currency);
        ConversionResult conversionResult = gson.fromJson(json, ConversionResult.class);
        CurrencyConverter currencyConverter = new CurrencyConverter(conversionResult);

        return currencyConverter.getConversionResult();
    }

    public static void converterMenu(String apiKey, String baseCode, String targetCode) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor que deseja converter: ");
        double amount = sc.nextDouble();
        CurrencyConverter currency = new CurrencyConverter(baseCode, targetCode, amount);
        System.out.printf("Valor %.2f [%s] corresponde ao valor final de %.2f [%s]%n", amount, currency.getBaseCode(), convertedCurrency(currency, apiKey), currency.getTargetCode());
    }
}
