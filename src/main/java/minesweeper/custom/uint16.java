package minesweeper.custom;

public class uint16 {
    private int value;

    // Constructor to initialize a uint16 value
    public uint16(int value) {
        this.value = validate(value);
    }

    // Method to validate and constrain the value to uint16 range
    private static int validate(int value) {
        if (value < 0 || value > 65535) {
            throw new IllegalArgumentException("Value must be an integer between 0 and 65535.");
        }
        return value;
    }

    // Method to get the value
    public int get() {
        return this.value;
    }

    // Method to set the value (with validation)
    public void set(int value) {
        this.value = validate(value);
    }

    // Override toString for easier debugging or logging
    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    // Example arithmetic operation (optional)
    public uint16 add(uint16 other) {
        return new uint16(this.value + other.get());
    }
}
