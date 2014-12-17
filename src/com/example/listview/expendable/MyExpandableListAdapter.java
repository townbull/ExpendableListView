
package com.example.listview.expendable;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Group> groups;
    public LayoutInflater inflater;
    public Activity activity;

    public MyExpandableListAdapter(Activity act, SparseArray<Group> groups) {
        activity = act;
        this.groups = groups;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_details, null);
        }
        text = (TextView) convertView.findViewById(R.id.textView1);
        text.setText(children);
        // Drawable image =
        // activity.getBaseContext().getResources().getDrawable(
        // R.drawable.ic_launcher );
        // text.setCompoundDrawables(image, null, null, null);
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, groupPosition + children,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }
        Group group = (Group) getGroup(groupPosition);
        TextView tv = (TextView) ((ViewGroup) convertView).findViewById(R.id.app_label);
        tv.setText(group.string);
        // ctv.setChecked(isExpanded);

        if (!isExpanded){
            ((ViewGroup) convertView).findViewById(R.id.header_layout).setVisibility(View.GONE);
        }
        else {
            ((ViewGroup) convertView).findViewById(R.id.header_layout).setVisibility(View.VISIBLE);

            ((TextView) ((ViewGroup) convertView).findViewById(R.id.header_ads))
                    .setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                            ScrollView scrollPane = new ScrollView(activity);
                            TextView txtPane = new TextView(activity);
                            StringBuilder contents = new StringBuilder();
                            txtPane.setText(contents);
                            scrollPane.addView(txtPane);
                            builder.setView(scrollPane);
                            builder.setTitle("Ads List");
                            builder.show();
                        }
                    });
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
