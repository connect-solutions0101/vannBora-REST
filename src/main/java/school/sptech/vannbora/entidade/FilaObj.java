package school.sptech.vannbora.entidade;

import lombok.Getter;

@Getter
public class FilaObj<T> {
    private T[] fila;
    private int inicio;
    private int fim;
    private int tamanho;

    public FilaObj(T[] fila, int inicio, int fim, int tamanho) {
        this.fila = fila;
        this.inicio = inicio;
        this.fim = fim;
        this.tamanho = tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia");
        }
        fim = (fim + 1) % fila.length;
        fila[fim] = info;
        tamanho++;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T elemento = fila[inicio];
        inicio = (inicio + 1) % fila.length;
        tamanho--;
        return elemento;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return fila[inicio];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            int i = inicio;
            for (int count = 0; count < tamanho; count++) {
                System.out.println(fila[i]);
                i = (i + 1) % fila.length;
            }
        }
    }
}