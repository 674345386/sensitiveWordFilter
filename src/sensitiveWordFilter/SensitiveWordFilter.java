
/**
* @Title: SensitiveWordFilter.java
* @Package d
* @Description: TODO
* @author cp
* @date 2016��7��6��
* @version V1.0
*/
package sensitiveWordFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ���� cp
 * @date ����ʱ�� 2016��7��6�� ����5:37:05
 * @version
 * @parameter
 * @return
 **/

public class SensitiveWordFilter {
	
	private int num; // ������ڶ��ٸ����д�����
	private HashSet<String> sensitiwordHashSet;//Ҫ���ҵ����д�set
	private Map sensitiveHashMap;		//�����дʹ����hashmap
	private HashSet<String> foundSensitiveWordSet; // ���ڱ����Ѿ����ֵ����д�

	
	
	/**
	 * @Description: TODO 
	 * @param 
	 * @return void
	 * @throws
	 */
	private void initFilter() {
		SensitiveWord instanceSensitiveWord = SensitiveWord.getInstance();
		instanceSensitiveWord.init();
		sensitiveHashMap =instanceSensitiveWord.getSensitiveHashMap();
		sensitiwordHashSet=instanceSensitiveWord.getSensitiwordHashSet();
		foundSensitiveWordSet = new HashSet<String>();
	}
	/**
	 * @Description: �����������ĵ��е�ÿ���ֿ�ͷ������������ֹ��ɵ�hashmap����ƥ��
	 * @param
	 * @return void
	 * @throws
	 */
	private void findSensitiveWordInTxt(String txtString) {
		for (int i = 0; i < txtString.length(); i++) {
			System.out.println("��    "+i+"���ֿ�ʼ֮����ĵ�");
			if (isContainSensitiveWord(i, txtString))
				num++;
		}
	}

	/**
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void printResult() {
		System.out.println("Ҫ���ҵ����д���     ");
		for (String word : sensitiwordHashSet) {
			System.out.println(word + "\t");
		}
		System.out.println("�ĵ��У���" + num + "�����д�  :  ");
		Iterator<String> iterator = foundSensitiveWordSet.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	/**
	 * @Description:
	 * @param @param i
	 * @param @param str
	 * @return �Ƿ�������д�
	 * @throws
	 */
	private boolean isContainSensitiveWord(int i, String txt) {
		int length = 0; // ��ƥ��������ֳ���,ÿ�Ҷ�һ���֣���1
		boolean isExist = false;
		Map nowWorkMap = sensitiveHashMap;
		for (int j = i; j < txt.length(); j++) { // �ӿ�ͷ��һ���ֿ�ʼƥ��
			Map wordMap = (Map) nowWorkMap.get(txt.charAt(j));
			System.out.println("��ǰ�����txt�е�word��" + txt.charAt(j));
			if (wordMap == null) {
				System.out.println("break");
				break; // �ĵ��еĵ�ǰ�ֲ�ƥ�����д��е��֣��˳�
			} else {
				length++;
				System.out.println(wordMap.get("isEnd").toString());
				// if (wordMap.get("isEnd").toString() == String.valueOf(1))
				if (wordMap.get("isEnd").toString().equals("1"))
					isExist = true;
				nowWorkMap = wordMap;
			}

		}

		if (isExist) {
			System.out.println("��Ӳ��ҵ������д�" + txt.substring(i, i + length));
			foundSensitiveWordSet.add(txt.substring(i, i + length));
		}
		return isExist;
	}

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
	
		SensitiveWordFilter sFilter = new SensitiveWordFilter();
		sFilter.initFilter();
		String txtString = "̫����˸��黳Ҳ��ֻ�������������� ӫĻ�е���ڣ����˹�������ȥ��ĳ�ַ�ʽ�����ĺ�����������ɱָ�ϻ���Щ�Լ��������˸С�"
				+ "Ȼ���ֹ� ���ǵİ��ݵĽ�ɫ���Ǹ��������˹���ϲ������� ŭ���ֶ�����ǣǿ�İ��Լ������Ҳ��������Ļ����У�Ȼ��ж������ᣬ"
				+ "�ѹ�������ĳһ���˵Ļ��ﾡ��Ĳ�����������ֻ���������һ����һ�����һ����Ӱ��ҹ����Ƭ ���˾������ϣ����ϵ绰�����ķ����š�";
//		String txtString ="�ձ�����";
		sFilter.findSensitiveWordInTxt(txtString);
		sFilter.printResult();
}
}
