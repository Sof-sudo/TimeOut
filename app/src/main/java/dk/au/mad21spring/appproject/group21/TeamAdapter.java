package dk.au.mad21spring.appproject.group21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    public interface ITeamItemClickedListener{
        void onTeamClicked(int index);
    }

    private ITeamItemClickedListener listener;
    private List<Team> TeamList;

    public TeamAdapter(ITeamItemClickedListener listener){
        this.listener = listener;
        TeamList = new ArrayList<>();
    }

    public void updateTeamList(List<Team> lists){
        TeamList = lists;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        TeamViewHolder vh = new TeamViewHolder(v, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return  TeamList.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // ui widgets
        ImageView imgCountry;
        TextView txtName;
        TextView txtTemp;
        TextView txtWeather;
        TextView txtRating;

        public TeamViewHolder(@NonNull View itemView, TeamAdapter.ITeamItemClickedListener teamItemClickedListener){
            super(itemView);

            listener = teamItemClickedListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onTeamClicked(getAdapterPosition());
        }
    }

}
