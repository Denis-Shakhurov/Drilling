package com.example.drilling.presentation.hole.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.drilling.R
import com.example.drilling.domain.model.GeologicalHole

class HoleAdapter(
    private var holes: List<GeologicalHole> = emptyList(),
    private val onEditClick: (GeologicalHole) -> Unit,
    private val onDeleteClick: (GeologicalHole) -> Unit,
    private val onItemClick: (GeologicalHole) -> Unit,
    private val onCopyClick: (GeologicalHole) -> Unit
) : RecyclerView.Adapter<HoleAdapter.HoleViewHolder>() {

    fun updateHoles(newHoles: List<GeologicalHole>) {
        this.holes = newHoles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int
    ): HoleAdapter.HoleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hole, parent, false)
        return HoleViewHolder(view)
    }

    override fun onBindViewHolder(holder: HoleAdapter.HoleViewHolder, position: Int) {
        holder.bind(holes[position])
    }

    override fun getItemCount(): Int = holes.size

    inner class HoleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvHoleName: TextView = itemView.findViewById(R.id.tvHoleName)
        private val tvDrillingRig: TextView = itemView.findViewById(R.id.tvDrillingRig)
        private val btnEdit: Button = itemView.findViewById(R.id.btnEditHole)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDeleteHole)
        private val btnCopy: Button = itemView.findViewById(R.id.btnCopyHole)

        fun bind(hole: GeologicalHole) {
            tvHoleName.text = hole.name
            tvDrillingRig.text = "Буровя ${hole.drillingRigNumber}"

            btnEdit.setOnClickListener { onEditClick(hole) }
            btnDelete.setOnClickListener { onDeleteClick(hole) }
            btnCopy.setOnClickListener { onCopyClick(hole) }

            itemView.setOnClickListener { onItemClick(hole) }
        }
    }
}