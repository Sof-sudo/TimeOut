package dk.au.mad21spring.appproject.group21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Team;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    public interface ITeamItemClickedListener{
        void onTeamClicked(int index);
    }

    private ITeamItemClickedListener listener;
    private List<Team> TeamList;

    public TeamAdapter(ITeamItemClickedListener listener){
        this.listener = listener;
        TeamList = new ArrayList<Team>();
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
        Glide.with(holder.imgTeamLogo.getContext())
                .load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+TeamList.get(position)
                        .getAbbreviation().toLowerCase()+".png").into(holder.imgTeamLogo);
        holder.txtTeamName.setText(TeamList.get(position).getFullname());
    }

    @Override
    public int getItemCount() {
        return  TeamList.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // ui widgets
        TextView txtTeamName;
        ImageView imgTeamLogo;
        ImageButton btnStar;

        public TeamViewHolder(@NonNull View itemView, TeamAdapter.ITeamItemClickedListener teamItemClickedListener){
            super(itemView);

            txtTeamName = itemView.findViewById(R.id.txtTeamName);
            imgTeamLogo = itemView.findViewById(R.id.imgTeamLogo);
            btnStar = itemView.findViewById(R.id.btnStar);
            btnStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btnStar.getBackground().equals(R.drawable.ic_star_foreground)){
                        btnStar.setBackgroundResource(R.drawable.ic_star_filled_foreground);
                    } else{
                        btnStar.setBackgroundResource(R.drawable.ic_star_foreground);
                    }
                }
            });
            listener = teamItemClickedListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onTeamClicked(getAdapterPosition());
        }
    }

}
