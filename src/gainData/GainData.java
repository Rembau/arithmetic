package gainData;
import java.util.Random;

/**
 * 获取随机数据
 * @author Administrator
 *
 */
public class GainData {
	static int N;
	public static int[] randomNum(){
		Random random=new Random();
		int[] data=new int[getSize()];
		for(int i=0;i<data.length;i++){
			data[i]=random.nextInt(getSize()+1);
		}
		return data;
	}
	public static void setSize(int n){
		GainData.N=n;
	}
	public static int getSize(){
		return N;
	}
}
