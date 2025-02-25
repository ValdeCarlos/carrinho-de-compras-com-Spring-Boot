import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras {
    private Map<String, Integer> itens; // Mapa para armazenar os itens (produto: quantidade)

    public CarrinhoDeCompras() {
        itens = new HashMap<>();
    }

    public void adicionarItem(String produto, int quantidade) {
        if (itens.containsKey(produto)) {
            itens.put(produto, itens.get(produto) + quantidade);
        } else {
            itens.put(produto, quantidade);
        }
        System.out.println(quantidade + "x " + produto + " adicionado(s) ao carrinho.");
    }

    public void removerItem(String produto) {
        if (itens.containsKey(produto)) {
            itens.remove(produto);
            System.out.println(produto + " removido do carrinho.");
        } else {
            System.out.println(produto + " não encontrado no carrinho.");
        }
    }

    public void visualizarCarrinho() {
        if (itens.isEmpty()) {
            System.out.println("O carrinho está vazio.");
        } else {
            System.out.println("Itens no carrinho:");
            for (Map.Entry<String, Integer> entry : itens.entrySet()) {
                System.out.println("- " + entry.getValue() + "x " + entry.getKey());
            }
        }
    }

    public double calcularTotal(Map<String, Double> precos) {
        double total = 0;
        for (Map.Entry<String, Integer> entry : itens.entrySet()) {
            String produto = entry.getKey();
            int quantidade = entry.getValue();
            if (precos.containsKey(produto)) {
                total += precos.get(produto) * quantidade;
            } else {
                System.out.println("Preço do produto " + produto + " não encontrado.");
            }
        }
        return total;
    }

    public double aplicarCupom(double total, String cupom) {
        Map<String, Double> cupons = new HashMap<>();
        cupons.put("DESC10", 0.10); // Cupom de 10% de desconto
        cupons.put("DESC20", 0.20); // Cupom de 20% de desconto

        if (cupons.containsKey(cupom)) {
            double desconto = total * cupons.get(cupom);
            double totalComDesconto = total - desconto;
            System.out.printf("Cupom %s aplicado! Desconto de R$ %.2f.%n", cupom, desconto);
            return totalComDesconto;
        } else {
            System.out.println("Cupom inválido.");
            return total;
        }
    }

    public static void main(String[] args) {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Map<String, Double> precos = new HashMap<>();
        precos.put("Camiseta", 50.0);
        precos.put("Calça", 120.0);
        precos.put("Tênis", 200.0);

        carrinho.adicionarItem("Camiseta", 2);
        carrinho.adicionarItem("Calça", 1);
        carrinho.visualizarCarrinho();

        double total = carrinho.calcularTotal(precos);
        System.out.printf("Total do carrinho: R$ %.2f%n", total);

        // Aplicando cupom de desconto
        double totalComDesconto = carrinho.aplicarCupom(total, "DESC10");
        System.out.printf("Total com desconto: R$ %.2f%n", totalComDesconto);
    }
}