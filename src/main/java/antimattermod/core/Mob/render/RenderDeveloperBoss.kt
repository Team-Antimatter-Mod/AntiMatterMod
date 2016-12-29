package antimattermod.core.Mob.render

import antimattermod.core.Mob.EntityDeveloperBoss
import antimattermod.core.Mob.model.ModelDeveloperBoss
import antimattermod.core.fileIO.DeveloperBossTexture
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.model.ModelBase
import net.minecraft.client.renderer.IImageBuffer
import net.minecraft.client.renderer.ThreadDownloadImageData
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.boss.BossStatus
import net.minecraft.util.ResourceLocation
import java.awt.image.BufferedImage
import java.awt.image.DataBufferInt
import java.io.File

/**
 * Created by kojin15.
 */
class RenderDeveloperBoss() : RenderLiving(ModelDeveloperBoss(), 0.5f) {
    
    var skin: ResourceLocation? = null
    
    //仮コード。後に書き換える
    override fun getEntityTexture(entity: Entity): ResourceLocation? {
        if (skin == null) {
            skin = ResourceLocation("AntiMatterMod:s/texture/raitichan.png")
            val texturemanager = Minecraft.getMinecraft().textureManager
            var obj = texturemanager.getTexture(skin)
            if (obj == null) {
                obj = ThreadDownloadImageData(File(DeveloperBossTexture.Raitichan.texturePath), null, AbstractClientPlayer.locationStevePng,
                        //==================================================================================================================
                        object : IImageBuffer {
                            
                            private var imageData: IntArray? = null
                            private var imageWidth: Int = 0
                            private var imageHeight: Int = 0
                            
                            override fun parseUserSkin(bufferedImage: BufferedImage?): BufferedImage? {
                                if (bufferedImage == null) {
                                    return null
                                } else {
                                    this.imageWidth = 64
                                    this.imageHeight = 64
                                    val bufferedimage1 = BufferedImage(this.imageWidth, this.imageHeight, 2)
                                    val graphics = bufferedimage1.graphics
                                    graphics.drawImage(bufferedImage, 0, 0, null)
                                    graphics.dispose()
                                    this.imageData = (bufferedimage1.raster.dataBuffer as DataBufferInt).data
                                    this.setAreaOpaque(0, 0, 32, 16)//頭部分を不透明に
                                    this.setAreaOpaque(0, 16, 64, 32)//胴体や腕等の旧テクスチャ部分を不透明に
                                    this.setAreaOpaque(16, 48, 48, 64)//新テクスチャの腕や足部分を不透明に
                                    
                                    this.setAreaTransparent(32, 0, 64, 16)//頭のウェアー部分がすべて不透明だった場合透明に
                                    
                                    if (bufferedImage.height < 64) {//旧型テクスチャの場合のコピー処理
                                        var bytes: Array<Int?>
                                        var bytes2: Array<Int?>
                                        
                                        pasteArea(32, 48, 48, 64, copyArea(40, 16, 56, 32))//左腕のテクスチャコピー
                                        mirrorArea(32, 52, 48, 64)//左腕のテクスチャ反転
                                        mirrorArea(36, 48, 40, 52)
                                        mirrorArea(40, 48, 44, 52)
                                        //1234 -> 2341
                                        bytes = copyArea(32, 52, 36, 64)//parts1
                                        bytes2 = copyArea(44, 52, 48, 64)//parts4
                                        pasteArea(44, 52, 48, 64, bytes)//1 -> 4 1231
                                        bytes = copyArea(40, 52, 44, 64)//parts3
                                        pasteArea(40, 52, 44, 64, bytes2)//4 -> 3 1241
                                        bytes2 = copyArea(36, 52, 40, 64)//parts 2
                                        pasteArea(36, 52, 40, 64, bytes)//3 -> 2 1341
                                        pasteArea(32, 52, 36, 64, bytes2)//2 -> 1 2341
                                        
                                        pasteArea(16, 48, 32, 64, copyArea(0, 16, 16, 32))//左足のテクスチャコピー
                                        mirrorArea(16, 52, 32, 64)//左足のテクスチャ反転
                                        mirrorArea(20, 48, 24, 52)
                                        mirrorArea(24, 48, 28, 52)
                                        //1234 -> 2341
                                        bytes = copyArea(16, 52, 20, 64)//parts1
                                        bytes2 = copyArea(28, 52, 32, 64)//parts4
                                        pasteArea(28, 52, 32, 64, bytes)//1 -> 4 1231
                                        bytes = copyArea(24, 52, 28, 64)//parts3
                                        pasteArea(24, 52, 28, 64, bytes2)//4 -> 3 1241
                                        bytes2 = copyArea(20, 52, 24, 64)//parts 2
                                        pasteArea(20, 52, 24, 64, bytes)//3 -> 2 1341
                                        pasteArea(16, 52, 20, 64, bytes2)//2 -> 1 2341
                                        
                                    }
                                    
                                    return bufferedimage1
                                }
                            }
                            
                            private fun copyArea(x: Int, y: Int, xEnd: Int, yEnd: Int): Array<Int?> {
                                val bytes: Array<Int?> = kotlin.arrayOfNulls((xEnd - x) * (yEnd - y))
                                var o = 0
                                for (i in x..xEnd - 1) {
                                    for (j in y..yEnd - 1) {
                                        bytes[o] = this.imageData!![i + j * this.imageWidth]
                                        o++
                                    }
                                }
                                return bytes
                            }
                            
                            private fun pasteArea(x: Int, y: Int, xEnd: Int, yEnd: Int, bytes: Array<Int?>) {
                                var o = 0
                                for (i in x..xEnd - 1) {
                                    for (j in y..yEnd - 1) {
                                        this.imageData!![i + j * this.imageWidth] = bytes[o] as Int
                                        o++
                                    }
                                }
                            }
                            
                            private fun mirrorArea(x: Int, y: Int, xEnd: Int, yEnd: Int) {
                                val bytes: Array<Int?> = kotlin.arrayOfNulls((xEnd - x) * (yEnd - y))
                                var o = 0
                                for (i in xEnd - 1 downTo x) {
                                    for (j in y..yEnd - 1) {
                                        bytes[o] = this.imageData!![i + j * this.imageWidth]
                                        o++
                                    }
                                }
                                o = 0
                                for (i in x..xEnd - 1) {
                                    for (j in y..yEnd - 1) {
                                        this.imageData!![i + j * this.imageWidth] = bytes[o] as Int
                                        o++
                                    }
                                }
                            }
                            
                            private fun setAreaTransparent(x: Int, y: Int, xEnd: Int, yEnd: Int) {
                                if (!this.hasTransparency(x, y, xEnd, yEnd)) {
                                    for (i in x..xEnd - 1) {
                                        for (j in y..yEnd - 1) {
                                            this.imageData!![i + j * this.imageWidth] = this.imageData!![i + j * this.imageWidth] and 16777215
                                        }
                                    }
                                }
                            }
                            
                            private fun setAreaOpaque(x: Int, y: Int, xEnd: Int, yExd: Int) {
                                for (i in x..xEnd - 1) {
                                    for (j in y..yExd - 1) {
                                        this.imageData!![i + j * this.imageWidth] = this.imageData!![i + j * this.imageWidth] or -16777216
                                    }
                                }
                            }
                            
                            private fun hasTransparency(x: Int, y: Int, xEnd: Int, yEnd: Int): Boolean {
                                for (i in x..xEnd - 1) {
                                    (y..yEnd - 1)
                                            .map { this.imageData!![i + it * this.imageWidth] }
                                            .filter { it shr 24 and 255 < 128 }
                                            .forEach { return true }
                                }
                                
                                return false
                            }
                            
                            override fun func_152634_a() {
                            }
                        }
                        //==================================================================================================================
                )
                
                texturemanager.loadTexture(skin, obj)
            }
        }
        return skin
    }
    
    fun doRender(entity: EntityDeveloperBoss, d0: Double, d1: Double, d2: Double, f0: Float, f1: Float) {
        BossStatus.setBossStatus(entity, false)
        super.doRender(entity as EntityLiving, d0, d1, d2, f0, f1)
    }
    
    override fun doRender(livingBase: EntityLivingBase, d0: Double, d1: Double, d2: Double, f0: Float, f1: Float) {
        this.doRender(livingBase as EntityDeveloperBoss, d0, d1, d2, f0, f1)
    }
    
    override fun doRender(living: EntityLiving, d0: Double, d1: Double, d2: Double, f0: Float, f1: Float) {
        this.doRender(living as EntityDeveloperBoss, d0, d1, d2, f0, f1)
    }
}