package school.sptech.vannbora.entidade;

import lombok.Getter;

@Getter
public class PilhaObj<Dependente> {
    private final Dependente[] pilha;
    private int topo;

    @SuppressWarnings("unchecked")
    public PilhaObj(int capacidade) {
        pilha = (Dependente[]) new Object[capacidade];
        topo = -1;
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo >= pilha.length - 1;
    }

    public void push(Dependente info) {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia!");
        }
        pilha[++topo] = info;
    }

    public Dependente pop() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo--];
    }

    public Dependente peek() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia!");
        }
        else {
            System.out.println("\nElementos da pilha:");
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }
}