package abc.core.subjectarea.equipment

import abc.core.subjectarea.view.Meta2GUIRegW2
import kotlinx.serialization.Serializable
import kotlin.math.pow

open class Meta2Register {
    open var unit: String = ""
    open var power: Int = 0
    open var regNum: Int = 0
    open var format: Int = 0
    open var type: Int = 0
    open var snapShot: Boolean = false
    open var envVar: String = ""
    open var valueWord: Int = 0
    open var valueWordSaved: Int = 0
    open var IEC60870RegNum: Int = 0
    open var high : Meta2Register? = null
    open var envVarValue: ArrayList<Double> = ArrayList()

    constructor() : this(0, 0)

    constructor(register0: Int, format0: Int) {
        regNum = register0
        format = format0
    }

    constructor(unit0 : String, power0: Int, regNum0: Int, format0: Int, type0: Int) {
        this.unit = unit0
        this.power = power0
        this.regNum = regNum0
        this.format = format0
        this.type = type0
    }

    fun getArrayLevel() : Int{
        var level = 0

        var entity: Meta2Register? = this
        while (entity != null) {
            if (entity is Meta2Array) {
                ++level
            }
            entity = entity.high
        }
        return level
    }

    fun doubleSize(): Boolean {
        return format == 2 || format == 3
    }

    fun saveValue() {
        valueWordSaved = valueWord
    }

    fun restoreValue() {
        valueWord = valueWordSaved
    }

    fun setValueWordLow(value: Int) {
        valueWord = valueWord and -65536 or (value and 0xffff)
    }

    fun setValueWordHigh(value: Int) {
        valueWord = valueWord and 0xffff or (value shl 16)
    }

    fun savedDataFormat(): Int {
        val code = format
        return if (code != 1) {
            code
        } else {
            if (power >= 0 && (envVar.length == 0)) {
                1
            } else {
                2
            }
        }
    }

    fun savedDoubleSize(): Boolean {
        val vv = savedDataFormat()
        return vv == 3 || vv == 2
    }

    fun Double.format(digits: Int): String = this.asDynamic().toFixed(digits)
    fun Float.format(digits: Int): String = this.asDynamic().toFixed(digits)

    fun regValueToString(logUnit: Int, vv: Int, metaGUI: Meta2GUIRegW2): String {
        if(metaGUI.byteSize) {
            return "" + vv.toByte()
        }
        else
        if(this.format == 2){
            return "" + Float.Companion.fromBits(vv)
        }
        else{
            val dd = regValueToFloat(logUnit, vv).toDouble()
            if (metaGUI.intValue) {
                return "" + dd.toInt()
            } else if (metaGUI.afterPoint != 0) {
                //return ("%6." + metaGUI.afterPoint + "f").format(dd)
                return dd.format(6)
                //return String.format("%6." + metaGUI.afterPoint + "f", dd).trim()
            } else {
                var cc = 0
                var xx = if (dd > 0.0) dd else -dd
                while (xx > 1.0) {
                    cc++
                    xx /= 10.0
                }
                if (power < 0) {
                    return dd.format(cc - power + 1)
                    //return String.format("%" + (cc - power + 1) + "." + -power + "f", dd, dd).trim()
                } else {
                    if (cc > 4) {
                        return dd.format(6)
                        //return String.Companion.format("%6.0f", dd)
                    } else {
                        return dd.format(5 - cc)
                        //return String.format("%6." + (5 - cc) + "f", dd).trim()
                    }
                }
            }
        }
    }

    fun regValueToFloat(logUnit: Int, vv: Int): Float {
        if (format == 7){
            return (vv and 255).toByte().toFloat()
        }
        else if (format == 2) {
            return Float.Companion.fromBits(vv)
        }
        else {
            var out : Float
            if (format != 1 && format != 4) {
                out = vv.toFloat()
            } else {
                var newVV = if (format == 1) vv.toShort().toInt() else vv and 0xffff
                if (power == 0) {
                    out = newVV.toFloat()
                } else {
                    out = (newVV.toDouble() * 10.0.pow(power.toDouble())).toFloat()
                //out = (newVV.toDouble() * Math.pow(10.0, power.toDouble())).toFloat()
                }
            }
            if (envVar != null && envVar.length != 0 && logUnit < envVarValue.size) {
                out *= envVarValue[logUnit].toFloat()
            }
            return out
        }
    }

    fun regValueToWord(logUnit: Int, vv: Int): Int {
        var ff = 0.0F
        var convert = false
        if (format == 7){
            return (vv and 255).toByte().toInt()
        }
        else if (format == 2) {
            return vv
        }
        else{
            if (format == 1) {
                var vv1 = vv.toShort().toInt()
                if (power != 0) {
                    convert = true
                    ff = (vv1.toDouble() * 10.0.pow(power.toDouble())).toFloat()
                }
            }
            if (envVar != null && envVar.length != 0 && logUnit < envVarValue.size) {
                ff = ((if (convert) ff else vv.toFloat()) * envVarValue[logUnit].toFloat())
                convert = true
            }
            if (convert) {
                return Float.Companion.fromBits(ff.toInt()).toInt()
            } else {
                return vv
            }
        }
    }

    override fun toString(): String {
        //return regNu
        //return "$regNum [${Values.formatNames[format]}] "
        return "Register.toString()"
    }

    fun setType(type: Int) {
        this.type = type
    }

    fun copyContent(dst: Meta2Register) {
        dst.regNum = regNum
        dst.format = format
        dst.type = type
    }


    fun size16Bit(): Int {
        return if (format != 2 && format != 3) 1 else 2
    }
}


