package algorithm.string.tries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Tries {

	TrieNode rootNode; 
	Tries() {
		this.rootNode = new TrieNode();
		this.rootNode.endWord = true;

	}
	public void add(String inputString) {

		boolean endCharacter = false;
		TrieNode currentNode = this.rootNode;
		for(int index=0;index<inputString.length();index++) {
			if(index == inputString.length() -1) {
				endCharacter = true;
			}
			currentNode = insertCharacter(currentNode, inputString.substring(index,  index + 1), endCharacter);

		}
	}
	private TrieNode insertCharacter(TrieNode root, String character, boolean endCharacter) {


		if(root.members.containsKey(character)) {
			return root.members.get(character);
		} else {

			// this means we have reached leaf
			// create a TrieNode and add character in the map
			// Mark the
			System.out.println("Creating node for Character:" + character);
			TrieNode leafNode = new TrieNode();
			leafNode.endWord = endCharacter;
			root.members.put(character, leafNode);
			return leafNode;
		}
	}	

	public boolean prefix(String substring) {
		return searchAll(substring, true);
	}
	public boolean search(String substring) {
		return searchAll(substring, false);
	}

	public boolean findLongestCharacter(String substring) {
		TrieNode prevNode = null;
		TrieNode currentNode = this.rootNode;
		for(int index=0;index<substring.length();index++) {
			String character = substring.substring(index, index + 1);
			currentNode = findCharacter(currentNode,character);
			if(currentNode == null) {
				// this means this character did' nt match
				if(index == 0) {
					return false;
				}
				if( prevNode == this.rootNode && index == 0) {
					return false;
				}
				if ( prevNode == this.rootNode && index > 0) {
					index = index -1;
				}
				
				if (prevNode.endWord == false) {
					return false;
				}
				currentNode = this.rootNode;
				index = index -1;
			}
			prevNode = currentNode;
		}
		
		
		return true;
	}
	private boolean searchAll(String substring, boolean prefix) {
		boolean endCharacter = false;
		TrieNode currentNode = this.rootNode;
		for(int index=0;index<substring.length();index++) {
			if(index == substring.length() -1) {
				endCharacter = true;
			}
			String character = substring.substring(index, index + 1);
			currentNode = findCharacter(currentNode,character);
			if(currentNode == null)
				return false;
		}
		if(prefix) {
			return true;
		}
		else if (currentNode.endWord == true) {
			return true;

		}
		return false;
	}

	public TrieNode findCharacter(TrieNode root, String character){


		if(root.members.containsKey(character)) {
			return root.members.get(character);
		} 
		return null;
	}

	public void printTrie() {
		printNode(this.rootNode);
	}
	
	public void delete(String substring) {
		
	}
	
	
	private void printNode(TrieNode node) {

		for(String member: node.members.keySet()) {
			System.out.println("Members:" + member + " Size:" + node.members.size());
			System.out.println("EndWord:" + node.endWord);
			System.out.println("----------------------");
			printNode(node.members.get(member));
		}
		if (node.members.size() == 0) {
			System.out.println("Empty Node");
			System.out.println("EndWord:" + node.endWord);
			System.out.println("----------------------");
		}


	}
}


class TrieNode {

	boolean endWord = false;
	Map<String, TrieNode> members = new HashMap();
}


