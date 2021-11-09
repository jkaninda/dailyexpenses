package com.jkantech.dailyexpenses.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jkantech.dailyexpenses.R
import com.jkantech.dailyexpenses.databinding.FragmentHomeBinding

class HomeFragment : Fragment(),View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var tvDailyExpense:TextView
    private lateinit var tvAmount:TextView
    private lateinit var tvSolde:TextView
    private lateinit var txtAllExpense:TextView
    private lateinit var tvAllAccount:TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.include.cardExpenses.setOnClickListener(this)
        tvDailyExpense=binding.include.txtDailyExpense
        tvAmount=binding.include.tvAmount

        tvAllAccount=binding.include.tvAllAccount
        tvSolde=binding.include.txtSolde
        txtAllExpense=binding.include.txtAllExpense


        initView()



        return root
    }

    private fun initView(){
        tvAmount.text="2000 USD"
        tvDailyExpense.text="100 USD"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        if (v!=null){
            when(v.id){
                R.id.card_expenses->{
                    findNavController().navigate(R.id.navigation_expense)
                }
            }
        }
    }
}