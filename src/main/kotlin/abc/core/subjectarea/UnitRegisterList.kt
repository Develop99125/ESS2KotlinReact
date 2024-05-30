package abc.core.subjectarea

import kotlinx.serialization.Serializable

@Serializable
class UnitRegisterList {
    var unitIdx = 0
    var data = ArrayList<Int>()

    fun size(): Int {
        return data.size
    }

    fun get(idx: Int): Int {
        return data[idx]
    }

    fun add(vv: Int) {
        data.add(vv)
    }

    fun getUnitIdx(): Int {
        return unitIdx
    }

    fun setUnitIdx(unitIdx: Int) {
        this.unitIdx = unitIdx
    }

    fun getData(): ArrayList<Int> {
        return data
    }

    fun setData(data: ArrayList<Int>) {
        this.data = data
    }
}

