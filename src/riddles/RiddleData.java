package riddles;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class RiddleData {
        // Linked Hash Map to store the riddles and the information about them (answer
        // and hint).
        private LinkedHashMap<String, RiddleInfo> riddleData = new LinkedHashMap<String, RiddleInfo>();
        private ArrayList<Riddle> unplayedRiddles;
        Random rand = new Random();

        // Initialise the Linked Hash Map and add the riddles to it.
        public RiddleData() {
                riddleData = new LinkedHashMap<String, RiddleInfo>();
                loadRiddles();

                this.unplayedRiddles = getRiddles();
        }

        // Method to add the riddles to the Linked Hash Map.
        private void addRiddles(String riddle, String answer, String hint) {
                riddleData.put(riddle, new RiddleInfo(answer, hint));
        }

        // Load the riddles into the Linked Hash Map.
        private void loadRiddles() {
                // African Forest Elephant riddles.
                addRiddles("I plant without hands, yet forests I grow,\n" +
                                "Carrying seeds wherever I go.\n" +
                                "Miles I wander, leaving life behind.\n" +
                                "Who am I, gentle and kind?", "African Forest Elephant",
                                "I help forests grow by spreading seeds from the food I eat.");
                addRiddles("You cannot spot me from up in the sky,\n" +
                                "No wings or machines can tell where I lie.\n" +
                                "Instead, on the ground, my clues are found.\n" +
                                "In little brown piles that scatter around.\n" +
                                "By counting these signs, they learn of my track…\n" +
                                "Who am I, hidden deep and hard to track?", "African Forest Elephant",
                                "What I leave behind tells more about me than seeing me ever could");
                addRiddles("My teeth are straight, my tusks are strong,\n" +
                                "Denser than most, yet hunted for long.\n" +
                                "A treasure to some, a curse to me.\n" +
                                "Who am I, caught in this plea?", "African Forest Elephant",
                                "What I carry is both my strength and my curse.");

                // Box Turtle riddles.
                addRiddles("My shell records the passing years,\n" +
                                "With rings that slowly reappear.\n" +
                                "Like trees, I mark the time I’ve grown.\n" +
                                "A timeline carved into my home.", "Box Turtle",
                                "Its shell shows growth patterns similar to rings in a tree.");
                addRiddles("I walk the world, yet barely roam,\n" +
                                "A tiny patch I call my home.\n" +
                                "Year by year, I stay so true,\n" +
                                "Even my bed is hardly new.", "Box Turtle",
                                "It doesn’t travel far and often stays in the same small area its whole life.");
                addRiddles("Look in my eyes and you might tell,\n" +
                                "A secret I wear very well.\n" +
                                "Fiery hues or shaded brown.\n" +
                                "My gender there is clearly found.", "Box Turtle",
                                "You can tell if it’s male or female by the color of its eyes.");

                // Lemur riddles.
                addRiddles("In my world, the girls take the lead,\n" +
                                "The boys step back and must concede.\n" +
                                "A rare rule among fur and claw.\n" +
                                "What social twist do I draw?", "Female Dominance",
                                "Flip the usual mammalian hierarchy.");
                addRiddles("I sit like a yogi in morning light,\n" +
                                "Belly to the sun, warm and bright.\n" +
                                "My fur is thin where rays can reach.\n" +
                                "What daily ritual do I teach?", "Sunbathing",
                                "posture that maximizes heat absorption with minimal movement.");
                addRiddles("I eat what others fear to try,\n" +
                                "A deadly plant that could make you die.\n" +
                                "Yet I dine daily, safe and sound.\n" +
                                "What toxic feast have I found?", "Cyanide Bamboo",
                                "A chemical defense in plants that typically interferes with cellular respiration.");

                // Kakapo riddles
                addRiddles("A scent so sweet, yet spells my doom,\n" +
                                "It fills the forest like perfume.\n" +
                                "It helps my kin to find me near.\n" +
                                "But hunters follow just as clear.", "Kakapo",
                                "What guides my kind also betrays my kind.");
                addRiddles("Across the hills, my love song hums,\n" +
                                "A booming call when nighttime comes.\n" +
                                "For miles it travels, deep and low.\n" +
                                "A forest drum few get to know.", "Kakapo",
                                "My voice travels farther than you’d expect.");
                addRiddles("I wait for fruit before romance,\n" +
                                "A patient bird who won’t take chance.\n" +
                                "Years may pass before I try.\n" +
                                "Timing matters, that’s no lie.", "Kakapo", "My family plans depend on trees.");

                // Brawn Bear Riddles
                addRiddles("I existed before I arrived.\n" + //
                                "I was made, then unmade from time itself\n" + //
                                "suspended between becoming and being\n" + //
                                "while my mother dreamed in darkness.\n" + //
                                "I was born in a cave before I ever drew breath in the world.\n" + //
                                "What phenomenon am I?", "Delayed Implantation",
                                " The timing of birth is deliberately delayed by the mother's biology.");
                addRiddles("My eyes may fail to see you well,\n" + //
                                "But far away, I catch your smell.\n" + //
                                "Across the land, through wind and air,\n" + //
                                "Your scent tells me that you are there.\n" + //
                                "What sense do I rely on most?", "A Powerfull Sense of Smell.",
                                "It’s the sense dogs are famous for too.");
                addRiddles("Not just strength but brains I wield,\n" + //
                                "Problem-solving is my field.\n" + //
                                "I’ve used a rock to scratch my back\n" + //
                                "A clever mind is what I pack.\n" + //
                                "What trait does this show?", "High Intelligence",
                                "This trait is what it what allows me to adapt, learn, and think rather than just act on instinct.");

                addRiddles("They call me ghost, yet I am real,\n" + //
                                "Through shadows dark I softly steal.\n" + //
                                "You won’t see me, you won’t hear\n" + //
                                "Until I suddenly appear.", "Leopard", "I’m famous for stealth and silence.");
                addRiddles("My coat is covered, not in dots,\n" + //
                                "But flower-shaped and shadowed spots.\n" + //
                                "They help me hide where light is dropped\n" + //
                                "In the jungle where I can’t be stopped.", "Leopard", "My spots are called rosettes.");
                addRiddles("Up I go with nimble tread,\n" + //
                                "Down I come, but first my head!\n" + //
                                "Few can match this daring feat—\n" + //
                                "Tree to ground on careful feet.", "Leopard",
                                "I’m one of the only big cats that climbs down headfirst.");
        }

        // Get the readles from the Linked Hash Map.
        public ArrayList<Riddle> getRiddles() {
                ArrayList<Riddle> riddleList = new ArrayList<Riddle>();

                // Loop through the Linked Hash Map and add the riddles into an ArrayList.
                for (Map.Entry<String, RiddleInfo> entery : riddleData.entrySet()) {
                        String riddle = entery.getKey();
                        RiddleInfo riddleInformation = entery.getValue();

                        String answer = riddleInformation.getAnswer();
                        String hint = riddleInformation.getHint();

                        riddleList.add(new Riddle(riddle, answer, hint));
                }
                // Return the riddle list.
                return riddleList;
        }

        // Get the riddles from the Linked Hash Map by a specific index.
        public Riddle getRiddlesByIndex(int index) {
                ArrayList<Riddle> riddleList = new ArrayList<Riddle>();

                // Loop through the Linked Hash Map and add the riddles into an ArrayList.
                for (Map.Entry<String, RiddleInfo> entery : riddleData.entrySet()) {
                        String riddle = entery.getKey();
                        RiddleInfo riddleInformation = entery.getValue();

                        String answer = riddleInformation.getAnswer();
                        String hint = riddleInformation.getHint();

                        riddleList.add(new Riddle(riddle, answer, hint));
                }

                // Check if the index is within the bounds of the list.
                if (index < 0 || index >= riddleList.size()) {
                        return null;
                }
                // Return the riddle at the specific index.
                return riddleList.get(index);
        }

        // Method that returns a random riddle from the list.
        public Riddle getRandomRiddle() {
                // Check if the copy of the riddle list is emplty and end the game.
                if (unplayedRiddles.isEmpty()) {
                        return null;
                }

                // If the array isn't empty then return the random riddle picked and delete it
                // form the copy list.
                int index = rand.nextInt(unplayedRiddles.size());
                Riddle pickedRiddle = unplayedRiddles.get(index);
                unplayedRiddles.remove(index);

                return pickedRiddle;
        }
}
