# Document Searcher

A command-line search engine written in Java that indexes a collection of documents and answers **Boolean** and **ranked** queries. Every data structure it uses (linked lists, stacks, BSTs, hash maps, sorted arrays) is implemented from scratch, without the Java Collections Framework.

The same queries can run against four different index structures, so you can compare how each one performs:

| Index structure | Description |
| --- | --- |
| Normal index | Scans each document's word list directly |
| Inverted index (linked list) | Each term maps to the list of documents that contain it |
| Inverted index (BST) | Terms are stored in a binary search tree for faster lookup |
| Inverted index (hash map) | Terms are stored in a chained hash map (load factor 0.7) |

## Features

- **Text preprocessing:** lowercasing, punctuation removal, and stop-word filtering (binary search over a sorted stop-word list)
- **Boolean retrieval:** queries with `AND` / `OR`, where `AND` binds more tightly than `OR` (e.g. `market AND investors OR sports`). The parser is stack-based.
- **Ranked retrieval:** documents are scored by term frequency across the query words and sorted with merge sort
- **Index statistics:** number of documents, tokens, and unique vocabulary terms

## Project structure

```
Document-Searcher/
└── src/
    ├── searchEngine.java      # Entry point (menu-driven CLI)
    ├── dataReader.java        # Loads the dataset, removes stop words, builds all indexes
    ├── QueryProcessing.java   # Boolean query parsing and evaluation
    ├── Ranking.java           # Term-frequency ranked retrieval
    ├── LinkedList.java, ArrayList.java, List.java, Node.java
    ├── LinkedStack.java, Stack.java
    ├── MyBST.java, MyBSTNode.java
    ├── hashMap.java, hashMapPair.java
    ├── rankingArray.java, PQElement.java     # Sorted result array (merge sort)
    ├── LinkedListobj.java, Pair.java, BooleanWrapper.java, Relative.java
    └── resources/
        ├── dataset.csv        # 50 short news-style documents
        └── stop.txt           # Stop-word list
```

## Getting started

**Requirements:** JDK 8 or newer.

Run these commands from the repository root. The program loads `src/resources/...` relative to the current directory, so it has to be started from there.

```bash
javac -d out src/*.java
java -cp out searchEngine
```

## Example session

```
=== Search Engine Menu ===
1. Boolean Retrieval
2. Ranked Retrieval
3. Show Indexed Documents
4. Show Indexed Tokens
5. Exit
Enter your choice: 2
...
Enter your search query: market investors
DocID   Score
0       2
12      2
41      1
```

```
Enter your Query: market AND investors
Result doc IDs: {0, 12}
```

## Complexity notes

The source code is annotated with Big-O estimates for the main operations. Examples:

- Building the indexes: `O(S + D·M·log S)`, where S = stop words, D = documents, M = words per document
- Ranking with the normal index: `O(D·W·L)`, where W = query words and L = document length
- Hash map lookups: `O(1)` on average, compared with `O(log V)` for the BST and `O(V)` for the linked-list index

## Dataset

`src/resources/dataset.csv` contains 50 short documents covering business, sports, technology, health, and other topics. After stop-word removal they contain 923 tokens and 646 unique terms.
