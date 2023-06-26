// Clase Symbol (símbolo)
abstract class Symbol {
    public abstract String asString();
    public abstract boolean equals(Symbol symbol);
}

// Clase Letter (letra)
class Letter extends Symbol {
    private char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    @Override
    public String asString() {
        return String.valueOf(letter);
    }

    @Override
    public boolean equals(Symbol symbol) {
        if (symbol instanceof Letter) {
            Letter otherLetter = (Letter) symbol;
            return this.letter == otherLetter.letter;
        }
        return false;
    }
}

// Clase Number (número)
class Number extends Symbol {
    private int number;

    public Number(int number) {
        this.number = number;
    }

    @Override
    public String asString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Symbol symbol) {
        if (symbol instanceof Number) {
            Number otherNumber = (Number) symbol;
            return this.number == otherNumber.number;
        }
        return false;
    }
}

// Clase SymbolWheel (rueda de símbolos)
abstract class SymbolWheel {
    protected Symbol[] symbols;
    protected int currentPosition;

    public SymbolWheel(Symbol[] symbols) {
        this.symbols = symbols;
        this.currentPosition = 0;
    }

    public abstract void spin();
    public abstract Symbol currentSymbol();
    public abstract String currentSymbolAsString();
}

// Clase LetterWheel (rueda de letras)
class LetterWheel extends SymbolWheel {
    public LetterWheel() {
        super(new Symbol[]{
                new Letter('A'),
                new Letter('B'),
                new Letter('C'),
                new Letter('D'),
                new Letter('E'),
                new Letter('F'),
                new Letter('G')
        });
    }

    @Override
    public void spin() {
        currentPosition = (currentPosition + 1) % symbols.length;
    }

    @Override
    public Symbol currentSymbol() {
        return symbols[currentPosition];
    }

    @Override
    public String currentSymbolAsString() {
        return symbols[currentPosition].asString();
    }
}

// Clase NumberWheel (rueda de números)
class NumberWheel extends SymbolWheel {
    public NumberWheel() {
        super(new Symbol[]{
                new Number(0),
                new Number(1),
                new Number(2),
                new Number(3),
                new Number(4),
                new Number(5),
                new Number(6)
        });
    }

    @Override
    public void spin() {
        currentPosition = (currentPosition + 1) % symbols.length;
    }

    @Override
    public Symbol currentSymbol() {
        return symbols[currentPosition];
    }

    @Override
    public String currentSymbolAsString() {
        return symbols[currentPosition].asString();
    }
}

// Clase OneArmedBandit (máquina tragamonedas de un solo brazo)
class OneArmedBandit {
    private SymbolWheel wheel1;
    private SymbolWheel wheel2;
    private SymbolWheel wheel3;

    public OneArmedBandit(Symbol symbol) {
        if (symbol instanceof Letter) {
            wheel1 = new LetterWheel();
            wheel2 = new LetterWheel();
            wheel3 = new LetterWheel();
        } else if (symbol instanceof Number) {
            wheel1 = new NumberWheel();
            wheel2 = new NumberWheel();
            wheel3 = new NumberWheel();
        }
    }

    public boolean spin() {
        wheel1
