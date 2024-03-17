package se.linerotech.module206.project1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import se.linerotech.module206.project1.onboarding.OnBoard2Fragment
import se.linerotech.module206.project1.onboarding.Onboard1Fragment
import se.linerotech.module206.project1.onboarding.Onboard3Fragment

class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> Onboard1Fragment()
            1 -> OnBoard2Fragment()
            else -> Onboard3Fragment()
        }
    }
}