// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/test/resources-proto/KftcFinancialInstitutionInfo.proto
// Protobuf Java Version: 4.26.1

package com.github.jinahya.kftc.financial.institution.info.proto;

public final class KftcFinancialInstitutionInfoOuterClass {
  private KftcFinancialInstitutionInfoOuterClass() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      KftcFinancialInstitutionInfoOuterClass.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface KftcFinancialInstitutionInfoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfo)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string category = 1;</code>
     * @return The category.
     */
    java.lang.String getCategory();
    /**
     * <code>string category = 1;</code>
     * @return The bytes for category.
     */
    com.google.protobuf.ByteString
        getCategoryBytes();

    /**
     * <code>string code = 2;</code>
     * @return The code.
     */
    java.lang.String getCode();
    /**
     * <code>string code = 2;</code>
     * @return The bytes for code.
     */
    com.google.protobuf.ByteString
        getCodeBytes();

    /**
     * <code>string name = 3;</code>
     * @return The name.
     */
    java.lang.String getName();
    /**
     * <code>string name = 3;</code>
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>bool representative = 4;</code>
     * @return The representative.
     */
    boolean getRepresentative();
  }
  /**
   * Protobuf type {@code com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfo}
   */
  public static final class KftcFinancialInstitutionInfo extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfo)
      KftcFinancialInstitutionInfoOrBuilder {
  private static final long serialVersionUID = 0L;
    static {
      com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
        com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
        /* major= */ 4,
        /* minor= */ 26,
        /* patch= */ 1,
        /* suffix= */ "",
        KftcFinancialInstitutionInfo.class.getName());
    }
    // Use KftcFinancialInstitutionInfo.newBuilder() to construct.
    private KftcFinancialInstitutionInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
    }
    private KftcFinancialInstitutionInfo() {
      category_ = "";
      code_ = "";
      name_ = "";
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo.class, com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo.Builder.class);
    }

    public static final int CATEGORY_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object category_ = "";
    /**
     * <code>string category = 1;</code>
     * @return The category.
     */
    @java.lang.Override
    public java.lang.String getCategory() {
      java.lang.Object ref = category_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        category_ = s;
        return s;
      }
    }
    /**
     * <code>string category = 1;</code>
     * @return The bytes for category.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getCategoryBytes() {
      java.lang.Object ref = category_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        category_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CODE_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object code_ = "";
    /**
     * <code>string code = 2;</code>
     * @return The code.
     */
    @java.lang.Override
    public java.lang.String getCode() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        code_ = s;
        return s;
      }
    }
    /**
     * <code>string code = 2;</code>
     * @return The bytes for code.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getCodeBytes() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        code_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NAME_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";
    /**
     * <code>string name = 3;</code>
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 3;</code>
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int REPRESENTATIVE_FIELD_NUMBER = 4;
    private boolean representative_ = false;
    /**
     * <code>bool representative = 4;</code>
     * @return The representative.
     */
    @java.lang.Override
    public boolean getRepresentative() {
      return representative_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(category_)) {
        com.google.protobuf.GeneratedMessage.writeString(output, 1, category_);
      }
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(code_)) {
        com.google.protobuf.GeneratedMessage.writeString(output, 2, code_);
      }
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
        com.google.protobuf.GeneratedMessage.writeString(output, 3, name_);
      }
      if (representative_ != false) {
        output.writeBool(4, representative_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(category_)) {
        size += com.google.protobuf.GeneratedMessage.computeStringSize(1, category_);
      }
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(code_)) {
        size += com.google.protobuf.GeneratedMessage.computeStringSize(2, code_);
      }
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
        size += com.google.protobuf.GeneratedMessage.computeStringSize(3, name_);
      }
      if (representative_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(4, representative_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo)) {
        return super.equals(obj);
      }
      com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo other = (com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo) obj;

      if (!getCategory()
          .equals(other.getCategory())) return false;
      if (!getCode()
          .equals(other.getCode())) return false;
      if (!getName()
          .equals(other.getName())) return false;
      if (getRepresentative()
          != other.getRepresentative()) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + CATEGORY_FIELD_NUMBER;
      hash = (53 * hash) + getCategory().hashCode();
      hash = (37 * hash) + CODE_FIELD_NUMBER;
      hash = (53 * hash) + getCode().hashCode();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + REPRESENTATIVE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getRepresentative());
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfo)
        com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo.class, com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo.Builder.class);
      }

      // Construct using com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        category_ = "";
        code_ = "";
        name_ = "";
        representative_ = false;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_descriptor;
      }

      @java.lang.Override
      public com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo getDefaultInstanceForType() {
        return com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo.getDefaultInstance();
      }

      @java.lang.Override
      public com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo build() {
        com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo buildPartial() {
        com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo result = new com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.category_ = category_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.code_ = code_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.name_ = name_;
        }
        if (((from_bitField0_ & 0x00000008) != 0)) {
          result.representative_ = representative_;
        }
      }

      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo) {
          return mergeFrom((com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo other) {
        if (other == com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo.getDefaultInstance()) return this;
        if (!other.getCategory().isEmpty()) {
          category_ = other.category_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (!other.getCode().isEmpty()) {
          code_ = other.code_;
          bitField0_ |= 0x00000002;
          onChanged();
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          bitField0_ |= 0x00000004;
          onChanged();
        }
        if (other.getRepresentative() != false) {
          setRepresentative(other.getRepresentative());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                category_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                code_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
              case 26: {
                name_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000004;
                break;
              } // case 26
              case 32: {
                representative_ = input.readBool();
                bitField0_ |= 0x00000008;
                break;
              } // case 32
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.lang.Object category_ = "";
      /**
       * <code>string category = 1;</code>
       * @return The category.
       */
      public java.lang.String getCategory() {
        java.lang.Object ref = category_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          category_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string category = 1;</code>
       * @return The bytes for category.
       */
      public com.google.protobuf.ByteString
          getCategoryBytes() {
        java.lang.Object ref = category_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          category_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string category = 1;</code>
       * @param value The category to set.
       * @return This builder for chaining.
       */
      public Builder setCategory(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        category_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>string category = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearCategory() {
        category_ = getDefaultInstance().getCategory();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>string category = 1;</code>
       * @param value The bytes for category to set.
       * @return This builder for chaining.
       */
      public Builder setCategoryBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        category_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private java.lang.Object code_ = "";
      /**
       * <code>string code = 2;</code>
       * @return The code.
       */
      public java.lang.String getCode() {
        java.lang.Object ref = code_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          code_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string code = 2;</code>
       * @return The bytes for code.
       */
      public com.google.protobuf.ByteString
          getCodeBytes() {
        java.lang.Object ref = code_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          code_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string code = 2;</code>
       * @param value The code to set.
       * @return This builder for chaining.
       */
      public Builder setCode(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        code_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>string code = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearCode() {
        code_ = getDefaultInstance().getCode();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>string code = 2;</code>
       * @param value The bytes for code to set.
       * @return This builder for chaining.
       */
      public Builder setCodeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        code_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>string name = 3;</code>
       * @return The name.
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string name = 3;</code>
       * @return The bytes for name.
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 3;</code>
       * @param value The name to set.
       * @return This builder for chaining.
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        name_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearName() {
        name_ = getDefaultInstance().getName();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }
      /**
       * <code>string name = 3;</code>
       * @param value The bytes for name to set.
       * @return This builder for chaining.
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        name_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }

      private boolean representative_ ;
      /**
       * <code>bool representative = 4;</code>
       * @return The representative.
       */
      @java.lang.Override
      public boolean getRepresentative() {
        return representative_;
      }
      /**
       * <code>bool representative = 4;</code>
       * @param value The representative to set.
       * @return This builder for chaining.
       */
      public Builder setRepresentative(boolean value) {

        representative_ = value;
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <code>bool representative = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearRepresentative() {
        bitField0_ = (bitField0_ & ~0x00000008);
        representative_ = false;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfo)
    }

    // @@protoc_insertion_point(class_scope:com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfo)
    private static final com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo();
    }

    public static com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<KftcFinancialInstitutionInfo>
        PARSER = new com.google.protobuf.AbstractParser<KftcFinancialInstitutionInfo>() {
      @java.lang.Override
      public KftcFinancialInstitutionInfo parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<KftcFinancialInstitutionInfo> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<KftcFinancialInstitutionInfo> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoOuterClass.KftcFinancialInstitutionInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n;src/test/resources-proto/KftcFinancial" +
      "InstitutionInfo.proto\0228com.github.jinahy" +
      "a.kftc.financial.institution.info.proto\"" +
      "d\n\034KftcFinancialInstitutionInfo\022\020\n\010categ" +
      "ory\030\001 \001(\t\022\014\n\004code\030\002 \001(\t\022\014\n\004name\030\003 \001(\t\022\026\n" +
      "\016representative\030\004 \001(\010b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_github_jinahya_kftc_financial_institution_info_proto_KftcFinancialInstitutionInfo_descriptor,
        new java.lang.String[] { "Category", "Code", "Name", "Representative", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}