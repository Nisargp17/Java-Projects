import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedListIterator<T> implements Iterator<T> {

    private CustomLinkedListNode<T> current;

    public CustomLinkedListIterator(CustomLinkedListNode<T> head) {
        this.current = head;
    }

    public boolean hasNext() {
        return current != null;
    }

    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        T data = current.data;
        current = current.next;
        return data;
    }

}
