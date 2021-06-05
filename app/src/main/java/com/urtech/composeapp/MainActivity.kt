package com.urtech.composeapp

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.urtech.composeapp.ui.theme.ComposeAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Search()
                }
            }
        }
    }
}

class MyViewModel : ViewModel() {

}

@Composable
fun Search() {
    val myViewModel: MyViewModel = viewModel()

    var searchText by remember {
        mutableStateOf("")
    }

    val listState0 = rememberLazyListState()

    Box {
        Column(modifier = Modifier.padding(10.dp)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("input something...") },
                colors = TextFieldDefaults.textFieldColors()
            )
            LazyColumn(state = listState0) {
                items(names.filter { it.trim().contains(searchText, ignoreCase = true) }) {
                    Text(
                        it.replaceFirst(" ", " : "), modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                    Divider()
                }
            }
        }
        FloatingActionButton(onClick = { /*TODO*/ }) {

        }
    }
}

val names = """
Aaron 艾伦 希伯来 巍然的高山；受神启示的。##
Abbott 艾布特 希伯来 父性的；伟大的精神。##
Abel 埃布尔 拉丁 生命；呼吸。##
Abner 艾布纳 希伯来 睿智；有智慧　。##
Abraham 亚伯拉罕 希伯来 崇高的父亲；众人之父。##
Adair 亚岱尔 苏格兰，爱尔兰 犹如像树般坚强。##
Adam 亚当 希伯来 天下第一个男人，男性。##
Addison 艾狄生 英国 亚当的后代。##
Adolph 阿道夫 德国 高贵的狼。##
Adonis 亚度尼斯 希腊 美男子。##
Adrian 亚德里恩 拉丁 傍亚德里亚海而居之人。##
Ahern 亚恒 塞尔特 马的主人。##
Alan 艾伦 斯堪的那维亚 英俊的，好看的；和睦，和平；高兴的。##
Albert 艾伯特 英国 高贵的聪明；人类的守护者。##
Aldrich 奥德里奇 英国 英明的统治者。##
Alexander 亚历山大 希腊 人类的保护者；人的帮手。##
Alfred 亚尔弗列得 英国；条顿 睿智的顾问；聪明帮手。##
Alger 阿尔杰 英国 光荣高贵护卫。##
Algernon 阿尔杰农 法国 满脸照胡子的人。##
Allen 艾伦 盖尔 和谐融洽；英俊的；好看的。##
Alston 奥斯顿 英国 出身高贵的人。##
Alva 阿尔瓦 拉丁 白种人的；金发碧眼的。##
Alvin 阿尔文 条顿 被大家所喜爱的；每个人的朋友。##
Alvis 亚尔维斯 挪威 短小精悍的人。##
Amos 亚摩斯 希伯来 任重道远的人。##
Andre 安得烈 法国 勇敢的，骁勇的。##
Andrew 安德鲁 希腊 男性的，勇敢的，骁勇的。##
Andy 安迪 希腊 男性的，勇敢的，骁勇的。##
Angelo 安其罗 意大利 上帝的使者。##
Augus 安格斯 盖尔 一个，唯一无二的；爱神。##
Ansel 安斯艾尔 法国 出身或教养均极高贵的人。##
Antony 安东尼 拉丁 值得赞美，备受尊崇的。##
Antoine 安托万 欧洲 值得赞美，备受尊崇的。##
Antonio 安东尼奥 拉丁 值得赞美，备受尊崇的。##
Archer 阿奇尔 英国 拉开千钧之弓的大力士。##
Archibald 阿奇柏德 英国 高贵的，勇敢的。##
Aries 亚力士 拉丁 公羊。##
Arlen 亚尔林 英国 誓约。##
Armand 亚尔曼 德国 军人。##
Armstrong 阿姆斯特朗 英国 臂力强健的人。##
Arno 阿诺 条顿 鹰。##
Arnold 阿诺德 条顿 鹰。##
Arthur 阿瑟 英国 高尚的或贵族的。##
Arvin 艾文 条顿 以平等之心待人者。##
Asa 亚撒 希伯来 上帝的赐予；治愈者。##
Ashbur 亚希伯恩 英国 入世的赛，传播喜讯者。##
Atwood 亚特伍德 英国 住在森林或森林中的人。##
Aubrey 奥布里 条顿 有钱有势的国王；小精灵。##
August 奥格斯格 拉丁 神圣的、尊崇的或身份高尚的人；八月。##
Augustine 奥古斯汀 拉丁 指八月出生的人。##
Avery 艾富里 英国 争斗；淘气，爱恶作剧的人。##
Baird 拜尔德 爱尔兰 很会唱民谣的人。##
Baldwin 柏得温 条顿 在战场很英勇的人。##
Bancroft 班克罗夫特 英国 种豆之人。##
Bard 巴德 英国 很快乐，且喜欢养家畜的人。##
Barlow 巴洛 希腊 住山中的人。##
Barnett 巴奈特 英国 领袖。##
Baron 巴伦 英国 勇敢的战士；高贵；男爵。##
Barret 巴里特 条顿 有大担当的人；熊。##
Barry 巴里 爱尔兰 优秀的射手；持矛者。##
Bartholomew 巴萨罗穆 希腊 是耶稣的十二门徒之一；农田之子。##
Bart 巴特 希腊 是耶稣的十二门徒之一。##
Barton 巴顿 英国 住在大麦田里的人。##
Bartley 巴特莱 英国 看管牧草地的人。##
Basil 巴泽尔 拉丁 像国王的。##
Beacher 比其尔 英国 原意浪涛。##
Beau 宝儿 法国 好修饰的人，花花公子。##
Beck 贝克 英国 溪流。##
Ben 班 希伯来 儿子；山峰。##
Benedict 班尼迪克 拉丁 受祝福的；能言善道的；神圣的。##
Benjamin 班杰明 希伯来 最喜爱的儿子；幸运的；雅各布的小孩。##
Bennett 班奈特 拉丁 受祝福的人。##
Benson 班森 希伯来，英国 具有其父般性格人。##
Berg 博格 条顿 冰山。##
Berger 格吉尔 法国 犁地的人；牧羊人。##
Bernard 格纳 条顿 像熊一般勇敢。##
Bernie 伯尼 条顿 像熊一般勇敢。##
Bert 伯特 英国 光辉灿烂；全身散发出荣耀和光辉的人。##
Berton 伯顿 英国 勤俭治产之人。##
Bertram 柏特莱姆 条顿 幸运且杰出的人。##
Bevis 毕维斯 法国 能明察周遭环境的人。##
Bill 比尔 德国 强而有力的战士或保护者。##
Bing 宾 德国 来自很特殊的村落。##
Bishop 毕夏普 英国 监督者。##
Blair 布莱尔 塞尔特 平原的；多沼泽的战场。##
Blake 布莱克 英国 漂白。##
Blithe 布莱兹 英国 很快乐的人。##
Bob 鲍伯 条顿 辉煌的名声。##
Booth 布兹 挪威 住小茅屋的人。##
Borg 博格 斯拉夫 居住在一座世袭古堡之中者。##
Boris 鲍里斯 俄国 战门；陌生人。##
Bowen 波文 英国 有教养的贵族。##
Boyce 柏宜斯 法国 住在森林中，独立者。##
Boyd 布德 塞尔特 金发的；白种人的。##
Bradley 布拉德利 英国 宽广的草地。##
Brady 布雷迪 英国 生气蓬勃的；宽广的岛屿。##
Brandon 布兰登 英国 发亮的山区。##
Brian 布莱恩 塞尔特和盖尔 有权势的领袖；出生高贵。##
Broderick 布拉得里克 斯堪的那维亚 著名的国王。##
Brook 布鲁克 英国 傍溪而居之人。##
Bruce 布鲁斯 法国 一座森林。##
Bruno 布鲁诺 意大利 褐色的或黑色的肤色。##
Buck 巴克 英国 雄鹿。##
Burgess 伯骑士 英国 自由的人。##
Burke 巴尔克 法国 住在城堡要塞的人。##
Burnell 布尼尔 法国 身材矮小者。##
Burton 波顿 英国 山丘上的小镇。##
Byron 拜伦 英国 乡下房舍，喜爱大自然景物者。##
Caesar 西泽 拉丁 皇帝；毛茸茸的。##
Calvin 卡尔文 拉丁 秃头的。##
Carey 凯里 韦尔斯 住在古堡里的人。##
Carl 卡尔 德国 伟大的人；男子汉。##
Carr 凯尔 挪威 住在沼泽的人。##
Carter 卡特 英国 架马车的人。##
Cash 凯希 拉丁 爱慕虚荣者，现金。##
Cecil 塞西尔 拉丁 视力朦胧的。##
Cedric 锡德里克 塞尔特 战争统帅；慷慨的。##
Chad 查德 英国 有经验的战士。##
Channing 钱宁 法国 牧师。##
Chapman 契布曼 英国 商人；小贩。##
Charles 查尔斯 拉丁－条顿 强壮的，男性的，高贵心灵，强健的。##
Chasel 夏佐 古法国 猎人。##
Chester 切斯特 罗马 小镇
Christ 克莱斯特 希伯来 基督。##
Christian 克里斯汀 希腊 基督的追随者，信徒。##
Christopher 克里斯托弗 希腊 基督的信差或仆人，表基督徒之意。##
Clare 克莱尔 拉丁 头脑清楚的。##
Clarence 克拉伦斯 拉丁 头脑清楚的；聪明的；著名的。##
Clark 克拉克 拉丁和英国 一位学者。##
Claude 克劳德 拉丁 跛脚者。##
Clement 克雷孟特 拉丁 和善和，仁慈的人。##
Cleveland 克利夫兰 英国 来自岩区的人。##
Cliff 柯利福 英国 来自陡峭的山区之人。##
Clifford 克利福德 英国 临近悬崖的道岔口，堡垒。##
Clyde 克莱得 韦尔斯 在很远的地方就听得到。##
Colbert 考伯特 英国 船员。##
Colby 考尔比 挪威 来自黑暗地区的人。##
Colin 科林 盖尔 小孩或婴儿。##
Conrad 康拉德 条顿 援助者，智慧；指导的人。##
Corey 科里 苏格兰 居住在湖边的人。##
Cornelius 康那理惟士 拉丁 太阳之角；王权
Cornell 康奈尔 法国 金黄色头发的人。##
Craig 克莱凸 塞尔特 居住峭壁的人。##
Curitis 柯蒂斯 法国 有礼貌的。##
Cyril 西里尔 希腊 贵族的。##
Dana 戴纳 英国 如阳光般纯洁、光耀。##
Daniel 丹尼尔 希伯来 上帝是我的仲判人。##
Darcy 达尔西 法国 指来自大城堡的人，黑人。##
Darnell 达内尔 希伯来 上帝是我的仲判人。##
Darren 达伦 爱尔兰 有成大事业的潜力之人。##
Dave 迪夫 希伯来 所爱的人。##
David 戴维 希伯来 所爱的人。##
Dean 迪恩 英国 山谷；学校的领导者；教堂的领导者。##
Dempsey 邓普斯 盖尔 骄傲而有力的人。##
Dennis 丹尼斯 希腊 希腊的酒神。##
Derrick 戴里克 德国 民族的统治者。##
Devin 得文 爱尔兰 指诗人或学者。##
Dick 狄克 德国 勇猛的，大胆的。##
Dominic 多米尼克 拉丁 属于上帝的。##
Don 唐 塞尔特 世界领袖。##
Donahue 唐纳修 爱尔兰 红褐色的战士。##
Donald 唐纳德 塞尔特 世界领袖；酋长。##
Douglas 道格拉斯 盖尔 来自黑海的人；深灰色。##
Drew 杜鲁 韦尔斯 聪慧与诚实的人。##
Duke 杜克 拉丁 公爵；领导者。##
Duncan 邓肯 盖尔 褐色的战士；晒得黑黑的战士。##
Dunn 唐恩 英国 指黑色皮肤的人。##
Dwight 德维特 条顿 白种人或金发碧眼的人。##
Dylan 迪伦 韦尔斯 海洋；波浪之神。##
Earl 额尔 英国 有敏锐智慧的高贵领导者。##
Ed 艾德 英国 一位有钱的监护人。##
Eden 伊登 希伯来 伊甸园，光芒与快乐。##
Edgar 埃德加 英国 快乐的战士。##
Edmund 埃德蒙 英国 有钱的保护者。##
Edison 爱迪生 英国 以照顾他人而丰富自己的人。##
Edward 爱德华 英国 一位很有钱的财产监护人。##
Edwiin 埃德温 英国 有钱的朋友；财产的获得者。##
Egbert 爱格伯特 条顿 非常有才能的，显赫的。##
Eli 伊莱 希伯来 伟大，杰出。##
Elijah 易莱哲 希伯来 耶和华就是上帝。##
Elliot 埃利奥特 法国，希伯来 虔诚信仰上帝的人。##
Ellis 埃利斯 希伯来 上帝是救世主。##
Elmer 爱尔玛 英国 高贵的或有名的。##
Elroy 埃尔罗伊 拉丁 王室的，国王。##
Elton 埃尔顿 英国 老农场的。##
Elvis 埃尔维斯 条顿 高贵的；朋友。##
Emmanuel 爱曼纽 希伯来 上帝与我们同在。##
Enoch 伊诺克 希腊，希伯来 追随者；虔诚的。##
Eric 艾利克 斯堪的那维亚 领导者。##
Ernest 欧内斯特 德国 热心、真实或诚挚的人。##
Eugene 尤金 希腊、拉丁 有高贵血统的。##
Evan 尔文 塞尔特 出身名门的人。##
Everley 伊夫力 英国 指野猪打斗的场地。##
Fabian 富宾恩 罗马 种豆之人。##
Felix 费利克斯 拉丁 幸福的或幸运的。##
Ferdinand 费迪南德 条顿 旅行，爱冒险的；谋和
Fitch 费奇 英国 金发之人。##
Fitzgerald 费兹捷勒 英国 技术高明的造箭家。##
Ford 福特 英国 河的渡口。##
Francis 弗朗西斯 拉丁 自由之人，无拘无束的人。##
Frank 法兰克 法国 自由之人。##
Franklin 法兰克林 拉丁或德国 自由之人。##
Frederic 弗雷得力克 德国 以和平领导的统治者；强大有力的，富有的。##
Gabriel 加布力尔 希伯来 上帝的男仆；上帝的力量是很力的。##
Gale 加尔 爱尔兰 唱歌；陌生人。##
Gary 盖理 条顿 带枪矛的人；猎犬。##
Gavin 盖文 爱尔兰 战争之鹰，胜利之鹰。##
Gene 吉恩 希腊，拉丁 有高贵血统的。##
Geoffrey 杰佛理 法国 神圣的和平。##
Geoff 杰夫 法国 神圣的和平。##
George 乔治 希腊 农夫。##
Gerald 杰拉尔德 条顿 勇敢的战士。##
Gilbert 吉尔伯特 条顿 闪耀的誓言；人质。##
Giles 贾艾斯 希腊 持盾之人。##
Glenn 葛兰 塞尔特 狭窄山谷的。##
Goddard 哥达 德国 稳固，不可动摇的定律。##
Godfery 高德佛里 法国 和平之神。##
Gordon 戈登 英国 三角峰的山区；英雄；强壮的人。##
Greg 格雷格 希腊 警觉之人。##
Gregary 格雷戈里 希腊 警觉之人。##
Griffith 葛里菲兹 韦尔斯 保护家园有力之人；红润的。##
Grover 格罗佛 英国 住在小树林中的人。##
Gustave 古斯塔夫 德国或瑞典 战争。##
Guy 盖 英国 引导者；明智的；木头；年老的战士。##
Hale 霍尔 英国 英雄般的荣耀。##
Haley 哈利 爱尔兰 科学的；有发明天份的。##
Hamiltion 汉米敦 法国或诺曼底 山上的小村；光秃的山丘。##
Hardy 哈帝 德国 勇敢，人格高尚之人。##
Harlan 哈伦 条顿 来自寒冷的国度。##
Harley 哈利 英国 到处是野兔的草原或小树林。##
Harold 哈罗德 英国 领导者；作战勇猛。##
Harriet 哈丽雅特 英国 战争，军人。##
Harry 哈里 中世纪英国 战争，军人。##
Harvey 哈维 法国 有苦味的；进步的或兴隆繁茂的。##
Hayden 海登 条顿 来自围以树篱的小镇。##
Heather 海拾兹 英国 开花的石南。##
Henry 享利 条顿 管理家庭的人；家族统治者。##
Herbert 赫伯特 德国 著名或辉煌的战士。##
Herman 赫尔曼 德国 军人；男子汉。##
Hilary 希拉里 拉丁 快乐的。##
Hiram 海勒 希伯来 身份地位高尚的；尊贵的。##
Hobart 霍伯特 德国 心中的光亭。##
Hogan 霍根 爱尔兰 永远年轻的。##
Horace 哈瑞斯 拉丁 老师。##
Howar 好尔德 条顿 看守者。##
Hubery 休伯特 法国 人格光明；思想灿烂的。##
Hugh 修 德国 理性；智力；灵魂。##
Hugo 雨果 拉丁 理性；智力；灵魂。##
Humphrey 汉弗莱 条顿 和平支持者。##
Hunter 汉特 英国 以打猎为荣的人。##
Hyman 海曼 希伯来 生命。##
Ian 伊恩 苏格兰 反映上帝荣耀之人。##
Ingemar 英格马 条顿 名门的后代。##
Ingram 英格兰姆 英国 指大鸟之子，智慧的象征。##
Ira 艾勒 希伯来 警觉性高的人；未阉割的马。##
Isaac 艾萨克 希伯来 笑声。##
Isidore 伊西多 希腊 女神爱色斯的礼物。##
Ivan 艾凡 俄国 上帝仁慈的赠礼。##
Ives 埃尔维斯 英国 指剑术家。##
Jack 杰克 希伯来 上帝仁慈的赠礼。##
Jacob 雅各布 希伯来 取而代之者；跟随者。##
James 詹姆士 拉丁 取而代之者。##
Jared 贾里德 希伯来 家世，血统，出身。##
Jason 杰森 希腊 治愈伤口的人；具备丰富知识的人。##
Jay 杰 法国 蓝鸟的美丽。##
Jeff 杰夫 法国 神圣的和平。##
Jeffrey 杰弗里 法国 神圣的和平。##
Jeremy 杰勒米 希伯来 上帝之崇高。##
Jerome 哲罗姆 拉丁 神圣的名字。##
Jerry 杰理 拉丁 神圣的名字。##
Jesse 杰西 希伯来 上帝的恩赐；上帝安在。##
Jim 吉姆 拉丁 取而代之者。##
Jo 乔 希伯来 上帝还会赐予。##
John 约翰 希伯来 上帝仁慈的赐恩。##
Jonas 琼纳斯 希伯来 和平鸽。##
Jonathan 强纳生 希伯来 上帝赐予。##
Joseph 约瑟夫 希伯来 上帝还会再赐予。##
Joshua 乔休尔 希伯来 上帝所援救。##
Joyce 乔伊斯 拉丁 欢喜的。##
Julian 朱利安 希腊 头发柔软的，也代表年青人。##
Julius 朱利尔斯 希腊 头发柔软的，毛茸茸的。##
Justin 贾斯丁 拉丁 诚实的。##
Keith 基思 爱尔兰 风；树林。##
Kelly 凯利 塞尔特 战士。##
Ken 肯恩 苏格兰 一位英俊的领导者。##
Kennedy 肯尼迪 爱尔兰 武士之首，指领导者。##
Kenneth 肯尼思 苏格兰 一位英俊的领导者。##
Kent 肯特 苏格兰 英俊的领袖；辽阔的国土。##
Kerr 科尔 爱尔兰 指持矛的黑人。##
Kerwin 科尔温 爱尔兰 有一只柔和的眼睛的人；朋友
Kevin 凯文 爱尔兰 圣人；很男性化的；出身很好的。##
Kim 金姆 英国 出生皇家堡垒草地上的人。##
King 金 英国 统治者。##
Kirk 科克 盖尔 住在教堂旁边的人。##
Kyle 凯尔 韦尔斯 一狭窄的海峡；英俊潇洒的。##
Lambert 蓝伯特 德国 聪明的治产者；光明。##
Lance 蓝斯 法国 土地；等待他人的人。##
Larry 劳瑞 拉丁 月桂树。##
Lawrence 劳伦斯 拉丁 月桂树。##
Leif 列夫 挪威 大众情人。##
Len 伦恩 条顿 强壮的狮。##
Lennon 伦农 爱尔兰 戴帽子穿斗蓬很瘦的人。##
Leo 利奥 希腊 狮；勇士。##
Leonard 伦纳德 条顿 强壮如狮。##
Leopold 利奥波德 德国 有爱国心的。##
Les 勒斯 塞尔特 来自古老的堡垒。##
Lester 里斯特 英国 营地；显赫之人。##
Levi 利瓦伊 希伯来 正联合在一起。##
Lewis 刘易斯 法国 在战场上很有名气。##
Lionel 赖昂内尔 法国 像狮子般的。##
Lou 路 法国 在战场上很有名气。##
Louis 路易斯 法国 在战场上很有名气。##
Lucien 陆斯恩 拉丁 光亮，真理。##
Luther 路德 德国 杰出的战士。##
Lyle 莱尔 法国 岛上之民。##
Lyndon 林顿 条顿 住在有菩提树的地方。##
Lynn 林恩 英国 傍湖而居者。##
Magee 麦基 爱尔兰 易发怒的人。##
Malcolm 麦尔肯 苏格兰 指传道者。##
Mandel 曼德尔 德国 指有杏仁眼的人。##
Marcus 马卡斯 罗马 指有侵略性的人。##
Marico 马里奥 拉丁 好战的人；苦战
Mark 马克 拉丁 指有侵略性的人。##
Marlon 马伦 法国 指像小鹰或猎鹰的人。##
Marsh 玛希 法国 来自草木丛生的地区。##
Marshall 马歇尔 英国 看守马的人，君王的跟随者。##
Martin 马丁 拉丁 好战的，尚武的。##
Marvin 马文 英国 朋友。##
Matt 马特 希伯来 上帝的赠礼。##
Matthew 马休 希伯来 上帝的赠礼。##
Maurice 摩里斯 拉丁 黑皮肤的；摩尔人的。##
Max 马克斯 拉丁 最伟大的。##
Maximilian 马克西米兰 拉丁 最伟大的。##
Maxwell 马克斯韦尔 英国 深具影响力又很值得尊敬之人。##
Meredith 马勒第兹 韦尔斯 大海的保护者。##
Merle 穆尔 法国 一只画眉鸟；法国人用这个名字去称呼那些爱唱歌或爱吹哨的人。##
Merlin 莫林 英国 海边的堡垒或海边的小山丘。##
Michael 麦克 希伯来 像上帝的人。##
Michell 米契尔 英国 犹如上帝的劳耀和高贵。##
Mick 密克 希伯来 像上帝的人。##
Mike 麦克 希伯来 像上帝的人。##
Miles 麦尔斯 德国，拉丁，希腊 战士；磨石；仁慈的。##
Milo 米路 拉丁 抚养镇民的人；战士。##
Monroe 门罗 塞尔特 红沼泽。##
Montague 曼特裘 拉丁 峭急之山脉的。##
Moore 穆尔 法国 黝黑英俊的外表。##
Morgan 摩尔根 韦尔斯 指住在海边的人。##
Mortimer 摩帝马 法国 傍着静寂的湖泊居住的人。##
Morton 摩顿 英国 来自旷野之村落。##
Moses 摩西 希伯来 从海中救人的人；小孩。##
Murphy 摩菲 爱尔兰 指捍卫海强的人。##
Murray 莫雷 塞尔特 水手。##
Myron 麦伦 希腊 芳香的；甜的，芳香的。##
Nat 纳特 希伯来 礼物。##
Nathan 奈登 希伯来 赠予者。##
Nathaniel 奈宝尼尔 希伯来 上帝的赠礼。##
Neil 尼尔 英国 勇敢的人；领袖；夺得锦标者，冠军。##
Nelson 尼尔森 英国 儿子。##
Newman 纽曼 英国 受欢迎的异乡人。##
Nicholas 尼克勒斯 希腊 胜利者。##
Nick 尼克 希腊 胜利者。##
Nigel 奈哲尔 拉丁 黑头发的人。##
Noah 诺亚 希伯来 镇静的，静止的，或平安的。##
Noel 诺尔 拉丁 生日；圣诞节。##
Norman 诺曼 法国 北欧人，斯堪的那维亚人。##
Norton 诺顿 英国 来自南方村落的人。##
Ogden 欧格登 英国 来自像树流域。##
Oliver 奥利佛 拉丁 平安的人。##
Omar 奥玛 阿拉伯 长子，受到先知的教诲。##
Orville 奥利尔 法国 来自黄金城。##
Osborn 奥斯本 英国 神圣的战士，受天赐福的人。##
Oscar 奥斯卡 条顿 神圣之矛。##
Osmond 奥斯蒙 英国 受到神圣的祝福，保护。##
Oswald 奥斯维得 英国 神圣而有力的。##
Otis 奥狄斯 希腊 听觉敏锐。##
Otto 奥特 德国 富有的。##
Owen 欧恩 拉丁，韦尔斯 出身高贵，年轻的战士。##
Page 裴吉 希腊 孩子。##
Parker 帕克 英国 看守公园的人。##
Paddy 培迪 爱尔兰 出身高贵的；贵族。##
Patrick 帕特里克 拉丁 出身高贵的；贵族。##
Paul 保罗 拉丁 指矮小玲珑的人。##
Payne 派恩 拉丁 来自乡村的人。##
Perry 斐瑞 英国 梨树。##
Pete 皮特 希腊 岩石，石头。##
Peter 彼得 希腊 岩石，石头。##
Phil 菲尔 希腊 爱马者。##
Philip 菲利普 希腊 战士；好战的或尚武的；爱马者。##
Porter 波特 法国 看门人或挑夫。##
Prescott 普雷斯科特 英国 牧师的小屋。##
Primo 普利莫 意大利 长子。##
Quentin 昆特 法国 第五，第五天。##
Quennel 昆尼尔 法国 独立的橡树区。##
Quincy 昆西 拉丁 第五。##
Quinn 昆 拉丁 第五。##
Quintion 昆顿 拉丁 第五个，第五个子孙。##
Rachel 雷契尔 希伯来 母羊。##
Ralap 雷尔夫 英国 狼的忠告或狼的智慧，顾问。##
Randolph 伦道夫 英国 狼的忠告或狼的智慧。##
Raymond 雷蒙德 德国 强而有力的保护者或顾问，保护者。##
Reg 雷哲 德国 帝王的；国王。##
Regan 里根 爱尔兰 帝王的；国王。##
Reginald 雷吉诺德 德国 强而有力的领导者。##
Reuben 鲁宾 希腊 一个儿子！新生者。##
Rex 雷克斯 拉丁 国王。##
Richard 理查德 德国 勇猛的，大胆的。##
Robert 罗伯特 条顿 辉煌的名声。##
Robin 罗宾 条顿 辉煌的名声，知更鸟。##
Rock 洛克 英国 岩石，非常强壮之人。##
Rod 罗德 英国 公路服务者；有名气的。##
Roderick 罗得里克 英国 很有名气；很出名的领导者。##
Rodney 罗德尼 英国 公路服务者；有名气的，仆人。##
Ron 罗恩 条顿 强而有权势的领导者。##
Ronald 罗纳德 条顿 强而有权势的领导者。##
Rory 罗里 塞尔特 红润，健康的人。##
Roy 罗伊 英国 国王。##
Rudolf 鲁道夫 条顿 狼。##
Rupert 鲁伯特 条顿 辉煌的名声。##
Ryan 莱安 爱尔兰 很有潜力的国王。##
Sam 山姆 希伯来 上帝之名。##
Sampson 辛普森 希伯来 高的智慧和力量，太阳的。##
Samuel 撒姆尔 希伯来 上帝之名。##
Sandy 山迪 拉丁 人类的防卫者。##
Saxon 撒克逊 英国 征服他人的持剑者。##
Scott 史考特 英国 苏格兰人，爱尔兰人。##
Sean 肖恩 爱尔兰 上帝仁慈的赠礼。##
Sebastian 夕巴斯汀 希腊 受尊敬的，庄严的。##
Sid 锡德 英国 来自菲尼基Sidon城。##
Sidney 锡得尼 英国 来自菲尼基Sidon城。##
Silvester 席尔维斯特 拉丁 来自森林。##
Simon 赛门 希伯来 聆德，扁鼻子的。##
Solomon 所罗门 希伯来 和平，平安。##
Spencer 史宾杜 英国 店主；治理者，行政官。##
Stan 史丹 英国 草原，牧场。##
Stanford 史丹佛 英国 来自多岩的津泊。##
Stanley 史丹尼 英国 草原，牧场。##
Steven 史帝文 拉丁，希腊 王冠，花冠。##
Stev 史帝夫 拉丁，希腊 王冠，花冠。##
Steward 史都华德 英国 看守者或管理者。##
Tab 塔伯 条顿 卓越，睿智。##
Taylor 泰勒 英国 做裁缝的人。##
Ted 泰德 古英国 有钱的监护人。##
Ternence 泰伦斯 拉丁 温和稳重或温柔的人；高塔。##
Theobald 希尔保特 拉丁 勇敢的神。##
Theodore 西奥多 希腊 神的赠礼或上帝的赠礼。##
Thomas 托玛士 英国，拉丁 太阳之神；一对孪生子。##
Tiffany 帝福尼 法国 显示上帝的神圣形象。##
Tim 堤姆 希腊 敬神或畏神。##
Timothy 帝摩斯 希腊 敬神或畏神。##
Tobias 托拜厄斯 希腊 上帝是我信仰的神。##
Toby 托比 希腊 上帝是我信仰的神。##
Todd 托德 英国 狐狸；指聪明狡猾的人。##
Tom 汤姆 英国，拉丁 一对孪生子太阳之神。##
Tony 汤尼 拉丁 值得赞美的，很受尊重的。##
Tracy 特瑞西 英国 市场小径，收获。##
Troy 特洛伊 法国 居住于卷发人群里的人。##
Truman 杜鲁门 英国 信仰很忠诚的人。##
Tyler 泰勒 英国 建盖屋顶的人，制砖瓦的人。##
Tyrone 泰伦 希腊 领主或统治者。##
Ulysses 尤里西斯 希腊 智勇双主，怀恨者。##
Upton 阿普顿 英国 来自镇上前端的人。##
Uriah 尤莱亚 希伯来 耶稣是光之所在。##
Valentine 范伦铁恩 拉丁 健康的人或强壮的人。##
Valentine 范伦丁 英国 有价值的，强壮的。##
Verne 佛能 拉丁 茂盛。##
Vic 维克 拉丁 胜利者，征服者。##
Victor 维克多 拉丁 胜利者，征服者。##
Vincent 文森 拉丁 征服。##
Virgil 弗吉尔 拉丁 春天，生物欣欣向荣之状。##
Vito 维托 拉丁 很活耀，气力旺盛的人。##
Vivian 维维安 拉丁 活跃的。##
Wade 维德 英国 流浪者。##
Walker 瓦尔克 英国 在树林中散步的人。##
Walter 瓦尔特 古德国，法国 指率领军队的人，或有权势的战士。##
Ward 华德 英国 保卫，护卫者。##
Warner 华纳 德国 抵抗侵略的人。##
Wayne 韦恩 英国 建造马车的人，四轮马车。##
Webb 韦勃 英国 编织者。##
Webster 韦伯斯特 英国 编织者。##
Wendell 温德尔 条顿 流浪者。##
Werner 弗纳尔 德国 卫国卫邦，御侵略之人。##
Wilbur 韦尔伯 英国 种很多柳树的城市，辉煌的。##
Will 威尔 法国 一位强而有力的战士或保护者。##
William 威廉 德国，法国 强而有力的战士或保卫者。##
Willie 威利 德国 强而有力的战士或保卫者。##
Winfred 威弗列德 韦尔斯 白色的波浪；爱好和平的朋友。##
Winston 温士顿 英国 来自朋友的市镇或居处，石头。##
Woodrow 伍德洛 挪威 居住林间小屋的人。##
Wordsworth 渥兹华斯 英国 在树林中散步的人。##
Wright 莱特 英国 伐木工人。##
Wythe 伟兹 法国 小战士。##
Xavier 泽维尔 西班牙 新房子的主人，光辉灿烂。##
Yale 耶鲁 古英国 来自边陲地带。##
Yehudi 耶呼弟 希伯来 膜拜上帝的人。##
York 约克 英国 养野猪的人。##
Yves 依夫 法国 法律的守护神。##
Zachary 扎克利 希伯来 为上帝所心仪的人。##
Zebulon 纪伯伦 希伯来 居处，住宅。##
Ziv 日杰夫 斯拉夫 到处充满活力及快乐
""".split("##")

fun flow() {

    runBlocking {

        repeat(Int.MAX_VALUE) {
            delay(50)
        }
        val flow: Flow<Int> = flow {
            (1..100).forEach {
                delay(1000L)
                emit(it)
            }
        }
        flow.collect { println(it) }

        var x = 0


    }
}

@Composable
fun Greeting(name: String) {
    val count: MutableState<Int> = remember {
        mutableStateOf(0)
    }

    var offset by remember { mutableStateOf(0f) }
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(count.value * 100) }


    Column {
        Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(5.dp)) {
            Text(text = "x=", modifier = Modifier.padding(top = 10.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.padding(5.dp)) {

                Box(contentAlignment = Alignment.BottomEnd) {
                    Button(
                        onClick = { count.value++ }) {
                        Text(text = "click me", fontSize = 15.sp)
                    }
//                    Text(text = "hei")
                }

                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Hello ${offset}!", fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }
        }
        Row {
            Column(Modifier.verticalScroll(state)) {
                repeat(120) {
                    Text(text = "Text$it", modifier = Modifier.padding(5.dp))
                }
            }
            LazyColumn() {
                items(120) { index ->
                    Text(text = "Item: $index", modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}

@Composable
fun Drag() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        Box(modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .background(Color.Blue)
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
        )
    }
}

/**
 * 滑动
 */
@ExperimentalMaterialApi
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}

/**
 * 多点触控：平移、缩放、旋转
 */
@ExperimentalFoundationApi
@Composable
fun TransformableSample() {
    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

    Box(
        Modifier
            // apply other transformations like rotation and zoom
            // on the pizza slice emoji
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y
            )
            // add transformable to listen to multitouch transformation events
            // after offset
            .transformable(state = state)
            .background(Color.White)
            .fillMaxSize()
    ) {
        val list = listOf("aaa", "bbbb", "cc", "dd", "ee", "cbc", "cdc")
        LazyColumn {
            list.forEach { item ->
                stickyHeader {
                    Text(
                        text = "我是头部${item.first()}",
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(20.dp),
                        color = Color.Red
                    )
                }
                items(3) {
                    Text(
                        text = "index$it",
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(40.dp),
                        color = Color.Black
                    )
                }
            }

        }
//        Image(
//            painter = painterResource(id = R.drawable.colmc),
//            contentDescription = null,
//            modifier = Modifier
//                .size(30.dp)
//                .clip(CircleShape)
//                .border(
//                    shape = CircleShape,
//                    border = BorderStroke(
//                        2.dp, Brush.linearGradient(
//                            colors = listOf(Color.Red),
//                            start = Offset(0f, 0f),
//                            end = Offset(100f, 100f)
//                        )
//                    )
//                )
//        )
    }
}

@Composable
fun MyApp(child: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            child()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
//        Greeting("Android")
//        Drag()
//        SwipeableSample()
//        TransformableSample()
        Search()
    }
}