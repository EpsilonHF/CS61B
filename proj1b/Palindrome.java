public class Palindrome {
    private LinkedListDeque<Character> deque;
    public Palindrome() {
        deque = new LinkedListDeque<Character>();
    }

    // convert string to deque
    public Deque<Character> wordToDeque(String word) {
        while (!deque.isEmpty())
            deque.removeFirst();
        for (int i = 0; i < word.length(); i++){
            char letter = word.charAt(i);
            deque.addLast(letter);
        }
        return deque;
    }

    // return true if string is palindrome
    public boolean isPalindrome(String word) {
        if (word.length() < 2)
            return true;
        LinkedListDeque<Character> lst = (LinkedListDeque)wordToDeque(word);
        while (lst.size() > 1) {
            Character l1 = lst.removeFirst();
            Character l2 = lst.removeLast();
            if (!l1.equals(l2))
                return false;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() < 2)
            return true;
        LinkedListDeque<Character> lst = (LinkedListDeque)wordToDeque(word);
        while (lst.size() > 1) {
            char l1 = lst.removeFirst();
            char l2 = lst.removeLast();
            if (!cc.equalChars(l1, l2))
                return false;
        }
        return true;
    }

}
