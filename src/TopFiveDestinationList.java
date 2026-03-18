import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("1. Kyoto is a city of temples, history and refined cuisine. Kaiseki, the Japanese art of haute dining, is perfected here with multi course seasonal meals that feel almost spiritual. But there is more to Kyoto than expensive meals, with matcha sweets, tofu dishes and yuba (tofu skin) making appearances in markets and casual restaurants. Kyoto is one of those Japanese food cities where even snacks have a ceremonial feel.", new ImageIcon(getClass().getResource("/resources/kyoto.webp")));
        addDestinationNameAndPicture("2. Hiroshima is a city that rebuilt itself after tragedy and has put its own twist on Japanese food culture. The star of the show here is Hiroshima style okonomiyaki, a layered pancake of noodles, cabbage, pork and sauce that is cooked in front of you on a hotplate. This is street food theatre at its best, and one of the true icons of Japanese food cities.", new ImageIcon(getClass().getResource("/resources/hiroshima.webp")));
        addDestinationNameAndPicture("3.Potong is located in a generations-old five-storey building that housed the family’s Chinese herbal medicine business from 1910. Restored over two and a half years in a major architectural project, the site now houses late-night cocktail haven Opium Bar on its fourth and fifth floors, the main dining room on the second floor, and on the third, an original room filled with hand-painted tiger drawings. Soon, the former first-floor apothecary will be home to Sino House: a ‘funky Thai-Chinese’ restaurant with live music.", new ImageIcon(getClass().getResource("/resources/potong_bangkok.jpg")));
        addDestinationNameAndPicture("4. Copenhagen’s Alchemist is redefining what it means to dine at the best restaurants in the world by creating an experience that transcends food. Chef Rasmus Munk describes his approach as “holistic cuisine,” blending gastronomy, science, art, and social commentary. Guests enter a vast dome where immersive visuals, sounds, and lighting accompany a tasting menu that can extend over 50 courses. The result is a sensory journey unlike anything else on the planet.", new ImageIcon(getClass().getResource("/resources/alchemist.webp")));
        addDestinationNameAndPicture("5. : New Orleans has always had a rich culinary history, with influences spanning French, Spanish, Vietnamese, African and more. The range of exceptional dining experiences – from the elegant, iconic Antoine’s to a neighbourhood po-boy joint like Parkway Bakery & Tavern – make this city unique. Try traditional dishes like shrimp Creole, jambalaya and red beans and rice at places all around town, or check out newer, award-winning restaurants combining Louisiana flavors with the likes of Senegalese (Dakar), Mexican (Acamaya) and Indian (Saffron).", new ImageIcon(getClass().getResource("/resources/new_orleans.webp")));
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);

        list.setCellRenderer(renderer);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}