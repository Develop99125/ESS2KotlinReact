import abc.core.subjectarea.ESS2Device
import abc.core.subjectarea.view.Meta2GUICollection
import abc.core.subjectarea.view.Meta2RegLink
import kotlinx.js.jso
import react.*

abstract class RComponent<P : Props, S : State> : Component<P, S> {
    constructor() : super() {
        state = jso { init() }
    }

    constructor(props: P) : super(props) {
        state = jso { init(props) }
    }

    open fun S.init() {}

    open fun S.init(props: P) {}

    abstract fun ChildrenBuilder.render()

    abstract fun putValue(vv: Long)
    abstract fun putValue(data: ArrayList<Int>)

    abstract fun clone() : RComponent<P, S>

    override fun render(): ReactNode = Fragment.create { render() }

    open fun getRegNum() : Int{
        return regNum.register.regNum
    }

    open var regNum: Meta2RegLink = Meta2RegLink()
    open var regNum2: Meta2RegLink = Meta2RegLink()
    open var regNum3: Meta2RegLink = Meta2RegLink()
    open var maxFail: Meta2RegLink = Meta2RegLink()
    open var maxWarn: Meta2RegLink = Meta2RegLink()
    open var minWarn: Meta2RegLink = Meta2RegLink()
    open var minFail: Meta2RegLink = Meta2RegLink()
    open var elem: Meta2GUICollection? = null

    open var device = ESS2Device()
    open var unitIdx = 0
    open var devUnit = 0
    open var regOffset = 0
    open var dxOffset = 0
    open var dyOffset = 0
    open var groupLevel = 0
    open var groupIndexes = arrayListOf(0, 0, 0, 0)

    companion object {
        const val DefaultTextSize = 14
        const val DefaultH = 20
        const val DefaultSpace = 5
        const val DefaultW2 = 50
    }

}

fun <S : State> Component<*, S>.setState(buildState: S.() -> Unit) {
    val partialState: S = jso {
        buildState()
    }
    setState(partialState)
}