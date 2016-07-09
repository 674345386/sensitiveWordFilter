package sensitiveWordFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author ���� cp
 * @date ����ʱ�� 2016��7��1�� ����10:27:54
 * @version 1.0
 * @parameter
 * @return
 **/
public class SensitiveWord {

	private HashSet<String> sensitiwordHashSet;  //����Ҫ���ҵ����д�
	private Map sensitiveHashMap;		//���дʹ����hashmap

	// Constructor
	private SensitiveWord() {
	}

	private static final SensitiveWord SENSITIVE_WORD = new SensitiveWord();

	public static SensitiveWord getInstance() {
		return SENSITIVE_WORD;
	}



	/**
	 * @Description: ��ȡ���д��ļ�������hashset��
	 * @param
	 * @return void
	 * @throws
	 */
	private void readfile() {
		sensitiwordHashSet = new HashSet<String>();
		String str;
		// FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		File file = new File("C:\\Users\\cp\\Desktop\\abc.txt");
//		File file = new File("C:/Users/cp/Desktop/abc.txt");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());

		if (!file.exists() || !file.exists())
			System.out.println("luj");
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(
					new FileInputStream(file));
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((str = bufferedReader.readLine()) != null) {
				sensitiwordHashSet.add(str);
				System.out.println(str);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // �ر���
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		// InputStreamReader inputStreamReader = new InputStreamReader(new
		// FileInputStream(file));
		// BufferedReader bReader = new BufferedReader(inputStreamReader);
		

	}

	private void consSensitiveHashMap(HashSet<String> wordsSet) {

		sensitiveHashMap = new HashMap(wordsSet.size());

		Map nowMap;
		Map temhashmap;
		String key;

		Iterator<String> iterator = wordsSet.iterator();

		while (iterator.hasNext()) {
			nowMap = sensitiveHashMap; // ��ǰ�����map
			key = iterator.next();

			for (int i = 0; i < key.length(); i++) { // ����ÿ���ʵ�ÿ����
				char oneWord_char = key.charAt(i);

				Object wordMap = nowMap.get(oneWord_char);// ���ҵ�ǰmap�����Ƿ��е�ǰ����
				// ���У����Ӧ��value(��Ӧ��hash��)��wordMap

				if (wordMap != null) { // �����������
					nowMap = (Map) wordMap;
				} else {
					temhashmap = new HashMap<String, String>();
					if (i != key.length() - 1) {
						temhashmap.put("isEnd", 0);
					} else {
						temhashmap.put("isEnd", 1);
					}

					nowMap.put(oneWord_char, temhashmap);
					nowMap = temhashmap;

				}

			}
		}

		// System.out.println(sensitiveHashMap.get("��"));

		Iterator iterator2 = sensitiveHashMap.entrySet().iterator();
		// Iterator iterator3 = sensitiveHashMap.keySet().iterator();
		while (iterator2.hasNext()) {
			Map.Entry entry = (Entry) iterator2.next();
			System.out.println("The key of hashmap is  "+entry.getKey());
			// Object keyObject =iterator2.next();
			// System.out.println(keyObject);
			// System.out.println(sensitiveHashMap.get(keyObject));
		}

	}
	
	
	/**
	 * @Description: ��ʼ��
	 * @param
	 * @return void
	 * @throws
	 */
	public void init() {
		readfile();
		consSensitiveHashMap(sensitiwordHashSet);

	}

	/**
	 * @return the sensitiwordHashSet
	 */
	public HashSet<String> getSensitiwordHashSet() {
		return sensitiwordHashSet;
	}

	/**
	 * @return the sensitiveHashMap
	 */
	public Map getSensitiveHashMap() {
		return sensitiveHashMap;
	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	//
	// HashSet<String> wordList = new HashSet<String>();
	// wordList.add("�й���");
	// wordList.add("�й�����");
	// wordList.add("�ձ���");
	// wordList.add("�ձ�����");
	//
	// SensitiveWord sensitiveWord =new SensitiveWord();
	// sensitiveWord.consSensitiveHashMap(wordList);
	// System.out.println();
	//
	//
	//
	// }

}
