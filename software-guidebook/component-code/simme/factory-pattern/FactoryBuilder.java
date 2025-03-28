import java.util.Hashtable;

class TestFactory {
    public static void main(String[] args) {
        // Predefined list of words to translate
        String[] wordsToTranslate = {"car", "submarine", "goat", "bird", "train"};

        DictionaryAdapterFactory factory = new DictionaryAdapterFactory();

        for (String wordToTranslate : wordsToTranslate) {
            System.out.println("Translating: " + wordToTranslate);

            // First try with Koenen
            IDictionaryAdapter adapter = factory.getDictionaryAdapter("koenen");
            String translation = adapter.translate(wordToTranslate);

            // If not found in Koenen, try with Kramers
            if (translation == null) {
                adapter = factory.getDictionaryAdapter("kramers");
                translation = adapter.translate(wordToTranslate);
            }

            // Display the result
            if (translation != null) {
                System.out.println("Translation of '" + wordToTranslate + "': " + translation);
                System.out.println("Source: " + adapter.getName());
            } else {
                System.out.println("Word '" + wordToTranslate + "' not found in any dictionary.");
            }

            System.out.println(); // Add a blank line between translations for readability
        }
    }
}

interface IDictionaryAdapter {
    String translate(String word);
    String getName();
}

class KoenenAdapter implements IDictionaryAdapter {
    private KoenenDictionary koenen;

    public KoenenAdapter() {
        koenen = new KoenenDictionary();
        koenen.openEnglishDutch(); // Initialize with English-Dutch translations
    }

    @Override
    public String translate(String word) {
        return koenen.lookUp(word);
    }

    @Override
    public String getName() {
        return "Koenen";
    }
}

class DictionaryAdapterFactory {
    public IDictionaryAdapter getDictionaryAdapter(String dictionaryname) {
        if (dictionaryname.equals("koenen")) {
            return new KoenenAdapter();
        }
        else if (dictionaryname.equals("kramers")) {
            return new KramersAdapter();
        }
        else {
            // Default or throw exception
            return null;
        }
    }
}

class KramersAdapter implements IDictionaryAdapter {
    private KramersDictionary kramers;

    public KramersAdapter() {
        kramers = new KramersDictionary();
    }

    @Override
    public String translate(String word) {
        return kramers.find(word);
    }

    @Override
    public String getName() {
        return "Kramers";
    }
}

class KoenenDictionary {
    private Hashtable words;

    /**
     * Creates an empty dictionary
     */
    public KoenenDictionary() {
        words = new Hashtable();
    }

    /**
     * Adds English-Dutch word pairs to dictionary
     */
    public void openEnglishDutch() {
        words.put("aeroplane", "vliegtuig");
        words.put("bicycle", "fiets");
        words.put("bird", "vogel");
        words.put("boat", "boot");
        words.put("car", "auto");
        words.put("cat", "kat");
        words.put("chicken", "kip");
        words.put("cow", "koe");
        words.put("dog", "hond");
        words.put("donkey", "ezel");
        words.put("elephant", "olifant");
        words.put("fish", "vis");
        words.put("fly", "vlieg");
        words.put("fox", "vos");
        words.put("horse", "paard");
        words.put("lion", "leeuw");
        words.put("rabbit", "konijn");
        words.put("monkey", "aap");
        words.put("mouse", "muis");
        words.put("pig", "varken");
        words.put("shark", "haai");
        words.put("sheep", "schaap");
        words.put("snake", "slang");
        words.put("tiger", "tijger");
        words.put("train", "trein");
        words.put("whale", "walvis");
    }

    /**
     * Adds Dutch-English word pairs to dictionary
     */
    public void openDutchEnglish() {
        words.put("aap", "monkey");
        words.put("auto", "car");
        words.put("boot", "boat");
        words.put("ezel", "donkey");
        words.put("fiets", "bicycle");
        words.put("haai", "shark");
        words.put("hond", "dog");
        words.put("kat", "cat");
        words.put("kip", "chicken");
        words.put("koe", "cow");
        words.put("konijn", "rabbit");
        words.put("leeuw", "lion");
        words.put("muis", "mouse");
        words.put("olifant", "elephant");
        words.put("paard", "horse");
        words.put("schaap", "sheep");
        words.put("slang", "snake");
        words.put("tijger", "tiger");
        words.put("trein", "train");
        words.put("varken", "pig");
        words.put("vis", "fish");
        words.put("vlieg", "fly");
        words.put("vliegtuig", "aeroplane");
        words.put("vogel", "bird");
        words.put("vos", "fox");
        words.put("walvis", "whale");
    }

    /**
     * Looks the word up in the dictionary and returns its translation
     *
     * @param word the word that will be searched in the dictionary
     * @return the translation of the word or null if the word is not found
     */
    public String lookUp(String word) {
        return (String) (words.get(word));
    }
}

class KramersDictionary {
    private String[][] list = { { "aeroplane", "vliegtuig" }, { "bicycle", "fiets" }, { "bird", "vogel" },
            { "boat", "boot" },
            { "car", "auto" }, { "cat", "kat" }, { "chicken", "kip" }, { "cow", "koe" }, { "dog", "hond" },
            { "donkey", "ezel" }, { "elephant", "olifant" }, { "fish", "vis" }, { "fly", "vlieg" }, { "fox", "vos" },
            { "goat", "geit" }, { "horse", "paard" }, { "lion", "leeuw" }, { "rabbit", "konijn" },
            { "monkey", "aap" }, { "mouse", "muis" }, { "pig", "varken" }, { "sheep", "schaap" },
            { "snake", "slang" }, { "submarine", "onderzeeboot" }, { "tiger", "tijger" }, { "train", "trein" } };

    /**
     * Creates and opens the English-Dutch dictionary
     */
    public KramersDictionary() {
    }

    /**
     * Looks the English word up in the dictionary and returns its Dutch translation
     *
     * @param english the English word that will be searched in the dictionary
     * @return the Dutch translation of the word or null if the word is not found
     */
    public String find(String english) {
        for (int i = 0; i < list.length; i++) {
            if (english.equals(list[i][0])) {
                return list[i][1];
            }
        }
        return null;
    }
}