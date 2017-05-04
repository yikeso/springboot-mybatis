package com.china.ciic.bookgenerate.common.util;

import com.google.common.base.Preconditions;

import java.text.DecimalFormat;

import static com.china.ciic.bookgenerate.common.constants.ParserConstants.*;


public class ContentHandle {
	
	public static void main(String[] agrs){
		long l = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis()-l);
		
		System.out.println(encode("<h3 id=\"CHP5-1-3\">法则4选择容易的办法往往会无功而返</h3>~~这是一个古代苏菲  （Suf i）故事的现代版：一个过路人看见一个醉鬼趴在路灯下寻找丢失的房门钥匙，于是就帮他找。找了一会儿才问醉鬼：“你在哪里丢的？”醉鬼说，就在房门外丢的。“那你为什么在这儿找？”过路人问。“因为房门外没有光亮。”醉鬼回答道。~~我们都对使用熟悉的方法解决问题感到放心，总是坚持我们最了解的办法。有时，钥匙可能恰巧就在路灯下，但更多时候会藏在黑暗的地方。不管怎样，假如解决方法真的能那么轻易地被发现，或对每个人都那么明显，那它可能早就已经被发现了。用熟悉的办法使劲儿努力，再努力，而深层的根本问题并没有改变，或更加恶化，乃是“非系统思考”的可靠指示信号—  我们经常把它称为“我们这儿需要更大号锤子”综合征。~~").length());
		System.out.println(encode(LZString.compress("<h3 id=\"CHP5-1-3\">法则4选择容易的办法往往会无功而返</h3>~~这是一个古代苏菲  （Suf i）故事的现代版：一个过路人看见一个醉鬼趴在路灯下寻找丢失的房门钥匙，于是就帮他找。找了一会儿才问醉鬼：“你在哪里丢的？”醉鬼说，就在房门外丢的。“那你为什么在这儿找？”过路人问。“因为房门外没有光亮。”醉鬼回答道。~~我们都对使用熟悉的方法解决问题感到放心，总是坚持我们最了解的办法。有时，钥匙可能恰巧就在路灯下，但更多时候会藏在黑暗的地方。不管怎样，假如解决方法真的能那么轻易地被发现，或对每个人都那么明显，那它可能早就已经被发现了。用熟悉的办法使劲儿努力，再努力，而深层的根本问题并没有改变，或更加恶化，乃是“非系统思考”的可靠指示信号—  我们经常把它称为“我们这儿需要更大号锤子”综合征。~~")).length());
		
/*		String e = encode(LZString.compress("50年代末，顺义县和全国其他地方一样，由于指导方针和许多政策脱离当时的社会实际，忽视科学态度，忽视客观经济规律，片面夸大人的主观能动作用，在生产力方面急于求成，在生产关系方面急于过渡，在分配方面搞平均主义，高指标、瞎指挥、浮夸风和共产风盛行，给农村经济带来了严重危害。1958年6月，顺义县将19个乡414个农业生产合作社建为8个农场，61个管理站。7月中旬又改建为8个人民公社，建农村公共食堂1199个。8月，县委扩大会议传达上级指示，要求反右倾、破保守，学习山东铁姑娘队白薯亩产50万斤，洛阳西红柿亩产11万斤的经验，对保守干部要警告、撤职。9月，全县实行全民公费医疗制。10月，县委决定61个管理站合并为33个，要求1959年粮食亩产达到5000斤指标。当时大讲“鼓足干劲搞生产，放开肚子吃饱饭”，粮食归大队，吃饭不要钱；大炼钢铁，砸了好锅；大搞深翻，不顾收粮；虚报产量，征购过头粮，终于带来了“低指标，瓜菜代”，使群众的劳动积极性和生产热情受到严重挫伤。1960年10月，县委贯彻中央精神，坚决纠正“五风”和“一平二调”的错误，全面退赔，并作出了具体退赔意见；11月下旬，县委扩大会决定继续发动群众，砍掉“五风”（共产风、浮夸风、命令风、特殊化风、瞎指挥风）。在中央着手解决“大跃进”、“人民公社化”运动中的错误，而顺义县干部群众正需要搞清思想认识和具体工作方法之际，邓小平同志来到顺义县视察，这一行真可谓是久旱逢甘露。邓小平同志对实际问题的处理和他在视察期间的指示，现在重温起来，依然感到十分亲切，具有一定的现实意义，回顾这次视察也可以看出邓小平同志相信和依靠群众的作风，实事求是的革命精神以及革命家的胆略、气魄和高瞻远瞩的胸怀。"));
		System.out.println(e);
*/	/*	String s = LZString.decompress(decode("23M2Mj010Bw9wL08wc80A0a16c1q070wfBM3F0s0A2NdgpGlP1Q$k7EPgiAR1VLQ0pRgijB0gJQ6yVgn09w1Ws0g1_Mz04U0r1A0KuE1x$MdLmwnVJ0e7a1TVk0TOE0J5g5GawsAR0bEG1w6c2DVE1gfg6VuwqjB0sNC0w1A392o7wtgcVWwjwJ0tIral7jo3Wvg3Rfek0UKgBF6g0mg0xPg0oBg7hBg1GVg20Zg2YcJA1_Vk17SQ1jhk0VZk1Nlc0sNk15sRcVg4$Jdfj0aY30dAt0dGt0lIl0g0p2M2MVg0wlg1RVg3hBlKb041kSM4nBg5kt9GX0cbAsM36$g1v0zzb01ud0bYl9M0FR4I0kLk0YXk0p2d90i3B0k6l003B0dkR0iUcug1ylkA1JJk0LJk0IXhUeg69oM6_Vg3oBek10OfSF40Acqkg1wiE1phbAl5ErMxw7PBg2oiD9D2Ue81d_c0Sna0s6d05OusA0n79F3zJcpoM1MpFd09SCw4gleOyg3eyI4kuIQGoe81hwQqsA0OLG0dZce5l02Kqw7NoZW0bHB07JGw37Zg3a2o0IvZ405o00MOg37sE1GsPAlhKmA0LSG0dQl06JOb4FFQ0X1pOg2cHI4Io0vXkFw0ykCW03KzO81_kQ0qJW0hA30dSuv8Uw3Vfg1oYzJ0a9FsA0L$G0csz08Kuw3dJg201E0OLizQs0htG08syC80VmQ08V60qfQV81N9Q0hKC03AQZ6ae81KekPw5_0M0liBR01iKsDmw7Nng3BsE1IFkFFs0F0r1g12dE048TrTvFw6_vvJ3M3zYo12eTr4fXw3Mj5O0snkqSMV80dHaNw70bpG0efsIo1ioSqw3JrsIS6W0hQkKPp0dJKuPAw4S$4GVM1AuE1jsQCw58Vg1pNc0hSl06rJeM94ln0p4Aa9JgOg0M7gAg0Gek0gPA8wM29AfqrJKA3g0LLnNn1Ic0bDkV6Ap304bLg0M5Q0jqYX00tyyg1qek06Zd00DBg1ekQ8UyNk0h1J01J5gcV7MAE1bLg1Zzc080Qc0AshAbIn0Ufigl04IzfhwQ0eKz009Vg0__c0a3B07a$n5$C7g1pVk0p_l03U5pF01ofg1t_k02eQV7zg03ek0hNQl40gpj04ndg0cbk0v3j05YTg1WQc0iHAKNlg1Mek04JcV40r5J06oQM1$ic08HZ07$P2bpdyzwC406aOaysM0Dfk02Ui$A0cSz02AlpF01ajg0p0bIsgUaQt9ZyQgj04VoM1Kpk0tdtLwwVlBc5g1CNmu2yqdEzF03sZv406ppKgQP8f46h03Gv2i8JQC8lmlpkoIfeM_jQg0CWd8g1Avc0pJyyI09GllSXeh03Vjg00vk0b8300lJg1w5k0hQP05JcDwV7mM0BvjQ0aV0_DX05wVfV04qkM1IvXQg0uxQ08FiLbAg0e3QCg1vjk0k5jUtnj906$$eg8s0fMP03pjlros0bj305azbJ07D4Kh043Zg1qmbDg0mjlmKh00C$o9vQCkx03BVgYV407pt01TwJl$I0vTB01b$g1QugUix6Q0uYZ043Pg1O0NKrJ05K7tB07nB68wc0qCd019V22F5l311HA5j07FoM1LvmoM0Wjk2eh02RRor00a$g1zqR8g0kKhImVi5e5N01yV8MTA0j5j07LEKgJ5bg001c05lyV2CHERA0tQl06lV5f07x3g11OdxSiEA0q3BmdJJidd6M1PLQ0mXZxAr1mV40m0Z04zz0ewY027_7NIaYrSqg1v5k04XA_Y0jOt02zH7E_ZfcYr40i7_X7m5T005Tg1F8Q0oYzxJ8fh067oM1J_9UpFi7UEiuxUg05_crpye5zKh01w0GFiElDT001QLVlQ0k3J05M3g1DwQ04Kz01zQjs7ew1jTiW9ig0GPpm0z2yg0c2G07nZ7YcgGzhA0fLqswQiD3S80v7t03TOE0p1yW2054Vg0zWq0284M06aW02pQNMHx47B7380eZj00p6hMg089HkhK4qtoW303UVE05Zie0Ck00TeWN0a04EXg0XH4$zA80nfBqy07N368w11pl1809HB03wiD8jew18sRecY3w8ytxVNT1a4Cq9024RKwM0_D9Uc0atOw1EtNmf0x1w0fJQ08vOdMV600dQxm70KwS3A3IbwMUW0s409rFwZ0zSc05fakj01JSxMg0oM605dnpEw0k6Q02bpdNtO06XTg0e$604HT3ww0fwc0006t9x0vmADg4cSUe204B5g0NI74NGl96NtjwPrB4e9mFF1uV7R82U8Mgse1tA09peswVO06XBs6kdEos40096127PrwX80rBIV205TUM0cM6j3V26scD8tDXdP6cgItx00AewqewohlhO408x6uFZXp400ZGw0_JdV7MKgviFbRRbzcQIw0mehnCEPV710316yMeMZZ00EsJ9g0kgWkk02taw0xsOmo00Sla0ZD878g0Er5PD1zyzwRI02Asx01oHE0pY2oxw6l1iM01kGwfmKNnhFJ03E0n8qmw0qsN$8b6wu300U1I8wYZofEN0lE05zl00PyhMg0z3G01uPgmAMX3B626yg0IVW05$UUsw0zvk0f3WFdMMs13E002ld4Y405EaA2V0aQ0cfWcH00h_x3g0xTpley1whkw1jQShfgx1WMUxe40agx6wDEs5QvFg0Uv4wSq46UdIr03zswAM0WnEwQ0fiCw0PemRBS0aw1$lc04fazae207aZg04371DQD8cOw1ojk04vhj3iFOB87YKw7RcpO401LNt08rJI0fz$f1O200tjckw1vx8xE08Rd00LQrSzJryP8s402XaSPXkI403Z6w15Nnqjoy6kse3kiJ7og0YmAEx$ewzww1Bpk0cMKdFRzhKa80jmR01vFwAM0YLaj5e80v3cvCj0Tz471eMjClk09fha8FTdKzuQKww1N1gUdqqiE9RFO43803hlGy04vt2k2iHk$g_E0micW0U8U7o7wHz00QaE0e1kDmXwc2Ms3F1006YE06XByws5aKknKPghAS406Upd0eToSq80t8ZCy02enpEw0NJQ08kSKz03l4o0eyl02bIn8g0waAOrhD8g05WAOs06d6swW1eINJM1_sbCf540556TplPWhCpc064B02GOBSpqcwJGawycGswsl00HRF1q2iNtOi4YP5eMw1X9jq6jr8IQEHA40caaO6kerc02MSbw0JSva7G99w12mc07jedyZ1SuoNMj6_kJy05fEM0T0q06m$kUFsV200Hzg0$DG0F8QqjJaZ09oxmcSZFbiSDihIiQKM7RuTij_5q1mEQiey5suIg0SAXKi4Jkg0CnH540c01q3FaYzA8UC8QB00W2E0qMsV2D6b809m302jyE0uPd026cn8g0lYG06Z55uFNSoupyE0cZQb8s2s80aCsX205bz5uWMsy540eN6w1_WaotJTrFN02OYE0g02Z1_nm808dl01h$D8nXw08tkno0bOYlVO2d4MF7vBCS00G3hXCfYi006Vg04D8gQuXZwtOfWetGpT8ckDqFcvgU808Nl020$Wh$Rj0VMV0Ub_zrsx02jAjglxg4V200eRu_WN02a5E0mXlyxxG305kGBqOi03Ifg05x69DER1_FeVhItaWtKegZz02Oix3iyt0nq02Klp2w1MQQ06ZO_AFRO1TsXZTw0MzbJ5eK3EXX6fb4m7$4w0NNjI9UvNxsV2zmAxM6pw1dlN8_I5iCr00dQo0shIqrsDJ_0TA$HsjdH4rkNsTOV0eeG5qmHNd03YxDDtoQBIy04NV1l3y8q02Ajk1E8XetH_8pBwBxC$ikRhMsmPh7Is5__k26SgQZwY0706VE8gs5otDEiPgqa02VBpE8diHBIHitz4FkPa008PfTKycgaPrU0fal2y04TZg0NJG022wWisx00RyE04_FL866m6e67Qt2bwy2Z6q8VEtyZy9ScwX_w0JEH8hh0vh$9O25104w7oqUptEe9rOEp$Uc203D3E0aAew0qYG01FpzLyMD8fm5$E083KFM68w09EXjr22Kb28Uy03P2Bgo08aSsw4yl1AMPYyIZGhekWqMoxzGLYkWZYAWouw0KZ5O202zsLuD8803juos30YGKyo0aEV0oeysqML_w0CLaBMs0SaG7ROj200AsGwlkFpBS6c38D82SbCc$HqwEiSeW80dQSnxcS$Gb8r0$20ysww0uKC00suLifqCLa0CQcQ0AE0blWUmdMw0oSB9200sIo0cxCoMJ1twf2E2w0oGG002eIQ80dDOw00aC1zd3N5J21xDUlG_XdG01PsFAvF8UD80ceMxM800VOw0qIHzHHVQU61fFO2023aFGwVAo784eLj3oE08wWswdOtia6QIbofSxgCsdMTGbo4Nw0nfUQ2aG02hOG10i8k7N80b3Cw0xkEs202u6o07tKw01UG01OsE0dvWw04rq02S3EQGGDq7c5GBlE03DC70s8yKdovMt0nEH2_C$1w0m0C004tzab2ngbcHECab4n1Lv68B9FyvqFccQdkfMqGzMagfkkAqAmQ800uCw0nt6011CE0aHO4cZow0h79JNS1ExS7T6hqD200Qgvm9yyAp5GiLM145ZDo8QzUKJft2R80ffmcw0N4gw0MIGNhsO00CqjcE071upcdMjQWqYsWgoE6UZSd04gImua8NMME2w08H5O0z7X200o18s80c1Ow0C0hfhZGr351O202z_E0dUpXIw0wcrSaHjUwRzNOBzV5ewAqA20NK0N202FuDg7YcUqUr8xUjC5Ug$13bUg4p0DoepE0ed1w0qfXd203fVCmo06b1pp91w0P_W00e3IQ806wuw0pOXd202buk55VcOIQ807lKw01cHd2JC00kVAelqE068Nw02E7d0jC02UsLfHZIQ808hVPgw0J7Dd200GyzscAo04pFCGUIw0AJXR4T0F7xq03XvKuo04o6dqrxkNvMmwsNZcvMuaw05b7fCoNS0gNQ2prDAIPDBl4s354j6zEea03$hz48TAA2hfF3_0cn8epFArAo089_PicytwQ5_A704O4Nw0uhFUHgkSbOEraXNSqEqr94mky03ZYKgaGFshM95HxoOxk9BxMbBLBGGB1j1s1o0eZOw0f1qD203cnIY9PLqAho097q8B$xw0zLG02PazoF_jMa8eI6mz4fkl9HUIQ72809CCu95hPgno4Nu9j0lhxVH2SSHyBuF0zNZKJovMH2t0VCWgdMw0n_G012exMdko63uvx80dduW5WgnBGUPNO4oE3SGURhNcGUNhaqsw3_oE3ywc0ktw9gXBnBw04EHCq01sVE027CGUrxw0IoF_k32qHv39wT2007nE0d1utwnB70w0qpB$2v0T5Q200zvE0ed_ZQGpZhw0O7akhK6D38hX1J23n3kI2039hDoemo08rCoyYG3yw0iYo8kyaW8s15AI1c31EtG$Oky01ZQp0BL3FrLINyOo09$Gw0I9plDjnRXSaHwPkTkT203kiKME04r6twP6vMKyC8oE7Qq8O4fQswhkvRn096VgZhoEa6swF0GphEj670WI6Ihwzorx7l7q69IhIdsww0t7G02liH3qK3FsQaK5ko0fuNswPMaM7gR4$5twvMVgId4cw0JqqcG01s_LUn$809GCw0jg4Y256tFtrdG1kR4T355J1jzDD$3$MUam2rUQhslO162012iHNnNrLAFpMvWFSCf8ZlRCc6umQb2qgaxxMfHiOqOn8elDg4wo03GKoNaNJOjejGw4vMXRj6Wgt0wRSAVV70jeWg2mw0eqG014RxPL2HEE00NOw0cZGVhVhS202DgoVnZlXqOpc4WDwE03I6twHSLS4cGxZow0mH7QFNrgNO202B0tFpSLm8ZC78s652Mcj80bSCfg9t$KZoK5noO6Mow04Gq01jrLaHKaGINKA80fzGb0Pgimw0B4HjluXuq031YhSLaDg6nqMJY806l_AMcFno70oMtwV0jeY_CcLS2m35SgK5bdtC6ZdyRCJCw0p0jnQvnzmLG02ApwM7ZnE9zbO5Bn880bMWXNb0VCjen08MEMrxw0hWqKEnij2V2EjYgs2rbLNO3IiJScHQmhqo9Q1uWoOY1l9s6UwY603PrCi80foesww0VEW01RsFc4$2lfUx8m_5k5RFccc6u801zOw0$kEs0QxS2J600CRL12FcQeAeW803BWFMw0bSXCq031SE0exaSgU2TGdlnZB4j0w0osE2MuUZ1W1h9L15i00ehD8180N9838cQ0oMNf82R4PwGrF981455ePfoBmz8802lOw0taH0m$pw2U1ScedS2OrJ0Bi3O02MaLyagfG4PKBvLeAbPnObEIx80cvaqwt0PqygqgnsWgYYsww0WIE9202byuV6AUL3M0oZ1802w44wK0Q4sww0ZbW025QqU_UNx1wcg1kToT8381K1xcg1w5U_P7o00SQ5LqccjaEkNpc9sqwPq6nnyKJ0P3838fF80ebanqnUzoAMNEw0jFCfpJ1tPQCHArC00_eouvfcQ9GKttI$io03ixs_OkEIhPTalgEU_8ZlEWEm4A4N5hmM70PJbBca3IypD4w06YG03kIo0eVuw0L24kqBy00Ncn880aaaE8w0v6oUwH0mUs3ce01hIhH3_44p0g6wJA9ewJJIc28E06l1fIw0XIH5202pdyM7yBSaJcIp5AWEb1r45z85Eckcqh2aEh460f5wq3vbd0OJSGQo06yqNg8OfUN6V56OjGDOYsNg7QimfyGxGRFMsjw0GnbK59q03yJyM6U3OCmx3fF7obJFy4q8WAWxYDe7od4fiHWKI6hxgiF4nynCnD8fWKkbDDDE07bCw0Ov602TasQ0_E0fj6w0zQ67Bg7dG03ufE03POpbVbc1708bfF0MNgKyqr70YHsFO4NgEWKy6670oYw0DgXoRURcp$2aUI3v200VSAHBMbm$Ybzr3tk3S8GxScQ800V1w0pQ603iCE0eCCswuq226i0KPrj6w0vApP0hSqpATzLCq9BLiHy01pOyMn84NqwTYU2W2N2Z2T2UyYyWyNyRyX6uk8sd6M51M802WCw0$xDw8Drf1$0fQgMfgDqHx9BO2ycN20Kj2XJgLUy016RxMiFj40CNM3DepnWHyRpjMgCQCOCT839Ca6TD0x8RdeIas58D8faE09nh4zwRNgng_8wRbBfaw071q03FTwVD3E0egG0KEwvRRbJbswZbONWM48HcPlSSvMw0g0DswAcAP5JS2tE1C01KhA6h8P$o068CTEeXAbcbQIIdw0BWr5IhTEmctaLOIsLTrWa00YXBGU07LNRg6ub0EWkCZoCG9HwZcGo5aMFM9kE8NtgA1lHh2hw0ZnVAPtTciSvSuwxBKMS03ahDorFRBhGR_qEqxAGWMBL2fwUAZ2IcYiEC5eHE025WswAmYGJ5twYXhky9agIOS711MQgmOEokKHcaKyAMimZC3cdCtCAmdyMoj5D9KrKyMo9phpw0J7FMpunzK7nqlO0UuMQBBO05nzJl4Ke6lS1zvBJrMoy00HsEvkdQNEZD820QwWwNnkNmngi9GFzeJ05OVo008u71T3564cN2XoigwEw0QsW01lSEwzm46nlyF807OSfloEEEXo2rNg0nqMlgOzw0jYHWi000VAFNMc5W5To3ZMH9lVya5YcNZfF9c9gE03yK70RnYayK_8BnVnC1rg1Dcwuwotpc21TCFMcwqDIy2IOmbww020C02CRw5EtbO1OWxGeDYcef40AcN9AU0eWSrUHyw0w0Aoy02nmE097ew0PzXd2xi02UeE03AVtKhKKAZd70yyw00$pm3Ixl9$201geE14DyTxX46i4C4C2gya8c05000bo03C4000Pw04U036y0dL00xHrM0aoe$Y1E0h2azy003WIQC4UwN000lw00WSY80g0uY00S00bEwd7R02XYDM0aVe$u$N$Y0y323000161040ogIQcwRIs4a8dMw05yG02m1z_eI0Me9pFHA803Wm9H0wFveby2zHPHwEhupuZ6$3__h_r6pkV0egXBswGUFwBwk8s22I0njzcEy7KyFwYGcCMMw0056032VDod6JS1CFCw_E07NOy270w0y85LjOFeG02K3BICp5I3oAWKYl26hGs6hFyeVql1McDwGPup7Aj6g00HGWMp$09400BSzaB$wQ04Utw2H5o7_3WZ3uNLkTK4Ah1O1003H4aMiqw0vHZIYaAs$fF5kF60HSSsrUcQ0dPf10M0kfc8048HnA9Qxwb7ge0PqfU8DAlysY4at0NwkFMN3f1iUfUm_D0k03zyhghoabxIOBNlOCVlayzEW0KybMIhz_0$RPcF7izJhO46O97Ww0l02MUD_b$7_7_0Jwguw00eYGa730WId4N2MBWGWljDx3402Y0j77Ky01DpiEFy8mimYvT7Y1MUaMrwFkg0avKcE123_2p9rMZYJh2vAQgLOK82GBy5CAo4U4u0AY1sp$5M6riq2Gesees4U4C0S9t41NkJwH34hU9WMDqn_131y1S2W2oEhoGMNX3OE8ow0eUQV0w0qfBWig8fc29hO7ks74w0nGcz0IQcB4H1X1O1WiM9hwvAbA2z3NxS$nh25j2HfU8oAMuD23ljaD1d4UZk2806TTg07ZKEWajyI5Ag13VoYbg01nhJ9eM5cd5Pn2V0aypUC3Cq29sMsfSoE9SwxwJUFEcgkU807lRvT53402jiyPjI0e91GB74Xcx403_hIs3A200A9nN0xkD0Hlv0LozMU4sye97szHAbI0X100HX6011Wb90idWsdy8G6IeSMicaxYGe18ZgU351FEtC60DozS283A1GgZ8pAbA1W1CwP4pE4U2GMt100S6E2U$ww0dRzTyVCfWGMwwEaNC8cOg09nNJ9eg68Bay00UenYi$N844ce1dQkcz$YtheWvkqCmuaqEZh707ksabs8wYac5dgqE05bdg026G70bu5UgUaK9f42IpEbUxZ7r3YgAMvNe0w84T0E4tIg0fVuWMaZIx32a55cgNQ9gzMle3CoEEZo3w805akM09zaTa9Reg400r5CL5Ea00V_c030iLv0TxS0Kxv0xwr2l5O00f4Qj9B0V47wcgw08bxq4MUG1ul0x1qz4gx828Q4fy301ogx8sw12140oz8gJ1segsnd5M2axFNKgKgahTQxQ1X4Ngv4fQbxNO01mw05g3ywcUDjBw0kzA3JNw0vMj00IEGj1PcKodN803Ltg03k64Ym1HCA02jhyU3A201FhiKaj00E6Ewa5o24irxJMUn96G8haz6RKM2d4PbED7H11XEs4U89JhExNlU97ljp6xe2200zJiGNaVXUw0ovBZUoPoEdS46pS1Gk1zhkeOTi3Hg8EsUkw7CA03WdLZ6aoO1X4fU0PDn5AwO0iu_zsg1kTcrF08ECUni9x5Sxupqq_MpU805OcUUa0yx3k1W0P0twdM3XU1EgG90Qt0bl818RiEdM6DBS0hEs1Wgw0eBjm42Z7_JY6u3eAPwlMdQBT7ADd1zwnopOqe1Vjd1YknosU804qBg0feanoiU802R$uu3s4t9f1Dw3AAuIQ1Njd0Z1RJlE9D1u0N00E6Nuu1Y1_1O0n9R79M6KOEH1100in9K9pWToauEdRxHH0U4xVauGn3Kxtx00uRW01JmceyR1OqnogY7Y1X33wGqnqfU1DBQjAEe0pxeKw9Ny2W8KMu2v59Ond3OFwMKyg02GqXcr3CKA7wP8yEF9piGFgx2gtn4NQ3wlbk03ChDI4t8ww8gPgq43g3t8s1Ug28eM72eEV0jCwG682niip8IAfN$M7Yh22MEwitMc1f3fm2hFi9zqOI16Do400zQq0zPOww0OGU6wf_401zSyMjI201LqR10EOYYPgd6hIzPPzFWMU_re28zwiw8U4w06QiKQt0g0bxaxAgcfO0hMuzLcZ9ce8umlwZwyEFM5CoS1X0s0JADqffcPa7Oo9Vkw03mRRwaYnWtxS301t00TUHcPmpYdjm9OgJ5kQz4dS0cz_cPGfJF9sAxihtco2I1e0n0vjayNKD0tHxcgjv3gf1EEoe0dckChpgHPtpOi2h26HEA001hE07FPeg7r9lj1xjoE4m6wH8RzFtIww0aARqaBN264l4baMQeGrx1N0VA00TjB28sjozA8uw6sjv2s0PyX0830jKAmPwT0Z0MWl30wM3aasCcV0nAxy2ny3lGew0kckV0DIy6A1bY7qOD9NMbTcVfv9v1vwlG681te$5n5LF70nWtddxFW55FF943E03mzdYlBgU0QhJIdy7ijh3DBplq8vM7wzh4020YydkJ0i3jww0tkQq82hfuhM2YaTRRVhoM0a7Ww0sgiJws2fuq0z53TObg$Mlscx5JacFv9plm_wUBSEe5PwQ8Kwa5m0nS9jww01_kIGw0cTzYgR7r3E3ehGxkwVIcg06vawRew0nf0mEzU5sem_wc5h50iDEEMpm4R8G3twyxCctx7o401yYDY2DhAxjwFr7BayfHAs1hMa6lKg6ieCDjMgVQFittf11o2LFU87WlK3o30sq17Qlq8u0tCUIZFF0a61Zb50qyNgg4VmbExpG016vk1Er8G5w0unkV0aM55eg4m8XEbgtwgcus1hh1j00iUr3dRwOIeShM27PWht4ZUNWD83yqHge0DyLU6UCV1Aw4mQQ_lfm1$4iPbyYG1W0ikgm00K9nlXtx00E06HxcoTo6U3_9aG00$1R0x1OtWnosw1DD80vhO0bceg7MHJjOMW0Mog07GNEUozeHlCO$jCw7Qfgdz1_y0021d710g8kQhrVct4N0G5Giw0hdsW0rUcgxZ0$ixpfUw09cj5qPgkU807icVUfqA00ssBoGG3S8acoMjY3C2uxjb3c70g0fr1B8r1I4lghE914sw3r1M5b21b6Iw0rpIV0iOaRdMk02hJH4xy031A8$oeg8P90Vpl003hoZ1JIhE6z7hCrh5oIGuwSuDrjQ6zR00C06Cswlfglld2KYMJx00jAXmQZ1d0j81rjaNQ0SWtwtuv2IZAS8$wy$g0fdVgZjIHiGFp3RhQ8Nfgp0BLEvkZ0OiiM802bzg04i_swguyM5ooK100pKFO0ue7gco2yLVklI9StDqqKQ1hm3cpg9d7YaRymht1x41sFpbEC_0qzSEwobljGFy1q10OgpfxutcZ1lF3sK8vydGKq27138k8Gqds5_3twD00kewcQCwFO13OxV748023bg03t6rxtodh5F9lE4hDYio1rwR5H8S0PgrltY0xX8h00cGW005dbA26Udw5Kg06NFw0lKJHgFYfy8CLvzW9A8sU6GKi6znt8S08yvdi9Q4lo2Z0fococ1nin0kD6rRtoeW24zXYuMmMkKa_TS3XUj_0k7d050tnS9KM0kbsktMU2k2X0s2nA9SyPl70X9SkK7bjk022yyW2CxDgv9oM2ALHjA38kGAg00"));
		System.out.println(s);
		String[] ss = s.split("~~");
		for(int i=0; i<ss.length; i++){
			if(i>16 && i<21)
			System.out.println(HtmlHandle.addParagraphHtml(ss[i], i));
		}*/
	}
	
	/**
	 * 字符解码
	 * @param s
	 * @return
	 */
	public static String decode(String s){
		Preconditions.checkNotNull(s, "");
		
		int len = s.length();
		
		int mod = Integer.valueOf(s.substring(0,1));
		
		int step = 1;
		
		String endBinary = s.substring(len-step);
		if("\n".equals(endBinary)){
			step = 2;
		}
		endBinary = mod==0 ? REVERSEMAP.get(s.substring(len-step,len-step+1)) : REVERSEMAP.get(s.substring(len-step,len-step+1)).substring(CUTSIZE-mod);
		String binaryStr = "";
		StringBuilder hexStrs = new StringBuilder();
		StringBuilder chinese = new StringBuilder();
		for(int i=1; i<len-step; i++){
			binaryStr += REVERSEMAP.get(s.substring(i, i+1));
			if(binaryStr.length()>=8){
				String hexStr = Integer.toHexString(Integer.valueOf(binaryStr.substring(0, 8),2));
				if(hexStr.length()==1){
					hexStrs.append("0");
				}
				hexStrs.append(hexStr);
				if(hexStrs.length()==4){
					chinese.append((char)Integer.parseInt(hexStrs.toString(),16));
					hexStrs = new StringBuilder();
				}
				binaryStr = binaryStr.substring(8);
			}
		}
		String endHex = Integer.toHexString(Integer.valueOf(binaryStr + endBinary,2));
		if(endHex.length()==1){
			hexStrs.append("0");
		}
		hexStrs.append(endHex);
		chinese.append((char)Integer.parseInt(hexStrs.toString(),16));
		return chinese.toString();
	}
	
	/**
	 * 字符编码
	 * @param s
	 * @return
	 */
	public static String encode(String s){
		Preconditions.checkNotNull(s, "传入字符为空");
		String ubStr = toUTF(s);
		int len = ubStr.length();
		int mod = len % CUTSIZE;
		int size =  len / CUTSIZE;
		StringBuilder newString = new StringBuilder();
		for(int i=0; i<size; i++){
			newString.append(MAP.get(ubStr.substring(0, 6)));
			ubStr = ubStr.substring(6);
		}
		if(mod > 0){
			newString.append(MAP.get(zeroFill("000000",ubStr)));
		}
		return String.valueOf(mod)+ newString.toString();
	}
	
	/**
	 * 补零
	 * @param s
	 * @return
	 */
	private static String zeroFill(String pattern, String s){
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(Long.parseLong(s));
	}

	/**
	 * 将字符转成utf8编码，并且把编码改成二进制
	 * @param inPara
	 * @return
	 */
	private static String toUTF(String inPara) {
        StringBuilder binary= new StringBuilder();
        for (int i = 0; i < inPara.length(); i++) {
        	String as = Integer.toHexString((inPara.charAt(i) + 0));
        	if(as.length()<4){
        		if(as.length()==2){
        			binary.append(hexString2binaryString("00" + as));
        		}else if(as.length()==3){
        			binary.append(hexString2binaryString("0" + as));
        		}else{
        			binary.append(hexString2binaryString("000" + as));
        		}
        	}else{
            	binary.append(hexString2binaryString(as));
        	}
        }
        return binary.toString();
    }
	/**
	 * 十六进制数转成二进制
	 * @param hexString
	 * @return
	 */
	private static String hexString2binaryString(String hexString){  
        if (hexString == null || hexString.length() % 2 != 0)  
            return null;  
        String bString = "", tmp;  
        for (int i = 0; i < hexString.length(); i++){  
            tmp = "0000"  
                    + Integer.toBinaryString(Integer.parseInt(hexString  
                            .substring(i, i + 1), 16));  
            bString += tmp.substring(tmp.length() - 4);  
        }  
        return bString;  
    }
}
