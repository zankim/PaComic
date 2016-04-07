/* ***************************************************
	^> File Name: ItemsFragment.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/04/06 - 19:24:19
*************************************************** */
package com.aoeiuv020.comic;
import com.aoeiuv020.stream.Stream;
import com.aoeiuv020.reptile.Reptile;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.content.*;
import android.view.*;
import android.os.*;
import android.widget.*;

import java.util.*;

import org.json.*;

public class ItemsFragment extends Fragment implements View.OnClickListener
{
	private ListView mListView=null;
	private ItemLoadAsyncTask mTask=null;
	private Button bLoadMore=null;
	private Reptile mReptile=null;
	private ItemAdapter mAdapter=null;
	public ItemsFragment()
	{}
	public ItemsFragment(Reptile reptile)
	{
		mReptile=reptile;
	}
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
		View view=inflater.inflate(R.layout.layout_fragment_items,container,false);
		mListView=(ListView)view.findViewById(R.id.listview);
		View vLoadMore=inflater.inflate(R.layout.layout_load_more,null);
		bLoadMore=(Button)vLoadMore.findViewById(R.id.button_load_more);
		bLoadMore.setOnClickListener(this);
		mListView.addFooterView(vLoadMore);
		mAdapter=new ItemAdapter(getActivity(),R.layout.layout_item,R.id.item_title,R.id.item_image,R.id.item_content);
		mListView.setAdapter(mAdapter);
		loadItems();
		return view;
    }
	private void loadItems()
	{
		mTask=new ItemLoadAsyncTask(mReptile,mAdapter,bLoadMore);
		mTask.execute(ItemLoadAsyncTask.FIRST);
	}
	@Override
	public void onClick(View view)
	{
		if(mTask.getStatus()==AsyncTask.Status.FINISHED)
		{
			mTask=new ItemLoadAsyncTask(mReptile,mAdapter,(Button)view);
			mTask.execute(ItemLoadAsyncTask.NEXT);
		}
	}
}
