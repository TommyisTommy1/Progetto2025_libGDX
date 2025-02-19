package DialogPkg;


public class Dialog {
    private String text;
    private String characterName;
    private String CharacterImage;

    public Dialog(String text, String characterName, String characterImage) {
        this.text = text;
        this.characterName = characterName;
        this.CharacterImage = characterImage;
    }

    public Dialog getDialog() {
        return this;
    }
}
