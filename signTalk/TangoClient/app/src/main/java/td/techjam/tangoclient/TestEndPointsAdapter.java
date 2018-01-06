package td.techjam.tangoclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestEndPointsAdapter extends RecyclerView.Adapter<TestEndPointsAdapter.TestEndPointViewHolder> {

    private DashboardActivity.TestEndPoint[] endpoints;
    private TestEndPointClickListener clickListener;

    public TestEndPointsAdapter(DashboardActivity.TestEndPoint[] endpoints, TestEndPointClickListener clickListener) {
        this.endpoints = endpoints;
        this.clickListener = clickListener;
    }

    @Override
    public TestEndPointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_test_endpoint, parent, false);
        return new TestEndPointViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestEndPointViewHolder holder, int position) {
        holder.btnTestEndpointLabel.setText(endpoints[position].toString());
    }

    @Override
    public int getItemCount() {
        return endpoints.length;
    }

    class TestEndPointViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.btn_test_endpoint_label)
        Button btnTestEndpointLabel;

        public TestEndPointViewHolder(View itemView) {
            super(itemView);

//            ButterKnife.bind(itemView);
            btnTestEndpointLabel = (Button) itemView.findViewById(R.id.btn_test_endpoint_label);

            btnTestEndpointLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        DashboardActivity.TestEndPoint endPoint = endpoints[getAdapterPosition()];
                        clickListener.onClicked(endPoint);
                    }
                }
            });
        }
    }

    interface TestEndPointClickListener {
        void onClicked(DashboardActivity.TestEndPoint endpoint);
    }
}
