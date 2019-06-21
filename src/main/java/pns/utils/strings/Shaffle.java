/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.strings;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpdw
 */
class Shaffle {

    public String shuffle(String input) {
	List<Character> characters = new ArrayList<Character>();
	for (char c : input.toCharArray()) {
	    characters.add(c);
	}
	StringBuilder output = new StringBuilder(input.length());
	while (characters.size() != 0) {
	    int randPicker = (int) (Math.random() * characters.size());
	    output.append(characters.remove(randPicker));
	}

	return output.toString();
    }
}
